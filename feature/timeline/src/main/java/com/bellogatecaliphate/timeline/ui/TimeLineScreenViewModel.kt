package com.bellogatecaliphate.timeline.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.bellogatecaliphate.core.model.dto.Advert
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.domain.GenerateIdUseCase
import com.bellogatecaliphate.domain.GetDateUseCase
import com.bellogatecaliphate.domain.comments.AddCommentUseCase
import com.bellogatecaliphate.domain.comments.DeleteCommentUseCase
import com.bellogatecaliphate.domain.comments.GetCommentRepliesUseCase
import com.bellogatecaliphate.domain.comments.GetCommentsUseCase
import com.bellogatecaliphate.domain.comments.SaveReplyToACommentUseCase
import com.bellogatecaliphate.domain.contents.GetContentsUseCase
import com.bellogatecaliphate.domain.contents.like.LikeContentUseCase
import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import com.bellogatecaliphate.nativeads.QuickReelsNativeAdProvider
import com.bellogatecaliphate.timeline.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class TimeLineScreenViewModel @Inject constructor(
	private val getLoggedInUserEmailUseCase: GetLoggedInUserEmailUseCase,
	private val getUserInfoUseCase: GetUserInfoUseCase,
	private val getContentsUseCase: GetContentsUseCase,
	private val likeContentUseCase: LikeContentUseCase,
	private val getCommentsUseCase: GetCommentsUseCase,
	private val getCommentRepliesUseCase: GetCommentRepliesUseCase,
	private val saveReplyToACommentUseCase: SaveReplyToACommentUseCase,
	private val deleteCommentUseCase: DeleteCommentUseCase,
	private val getDateUseCase: GetDateUseCase,
	private val addCommentUseCase: AddCommentUseCase,
	private val generateIdUseCase: GenerateIdUseCase,
	private val adProvider: QuickReelsNativeAdProvider
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	private val _listOfContents = MutableStateFlow<Flow<PagingData<Content>>?>(null)
	internal val listOfContents = _listOfContents.asStateFlow()
	
	private val _comments = MutableStateFlow<Flow<PagingData<Comment>>?>(null)
	internal val comments = _comments.asStateFlow()
	
	private val likeJobs = mutableMapOf<String, Job>()
	
	init {
		viewModelScope.launch {
			async { loadAds() }
			async { getContents() }
		}
	}
	
	fun getContents() {
		val response = getContentsUseCase().cachedIn(viewModelScope).map {
			it.map { content -> mapContent(content, adProvider) }
		}
		_listOfContents.value = response
	}
	
	fun onAdRequest() = viewModelScope.launch {
		val latestAd = adProvider.getNextAd()
		_uiState.update { it.copy(adVert = Advert(latestAd)) }
	}
	
	fun likeContent(contentId: String, isLiked: Boolean) = viewModelScope.launch {
		_uiState.update { current ->
			current.copy(
				mapOfLikedAndUnlikedContents = current.mapOfLikedAndUnlikedContents.toMutableMap()
					.apply {
						this[contentId] = isLiked
					}
			)
		}
		likeJobs[contentId]?.cancel()
		val job = viewModelScope.launch {
			try {
				likeContentUseCase(contentId, isLiked)
			}
			catch (e: CancellationException) {
				// job was cancelled → ignore.
			}
			catch (e: Exception) {
				_uiState.update { current ->
					current.copy(
						mapOfLikedAndUnlikedContents = current.mapOfLikedAndUnlikedContents.toMutableMap()
							.apply {
								this[contentId] = ! isLiked
							}
					)
				}
			}
		}
		
		likeJobs[contentId] = job
	}
	
	fun addComment(
		contentId: String,
		parentCommentId: String?,
		comment: String
	) = viewModelScope.launch {
		_uiState.update { it.copy(isUploadingComment = true, commentUploadedSuccessfully = null) }
		
		val cachedComment = Comment(
			commentId = generateIdUseCase(),
			userId = getLoggedInUserEmailUseCase() ?: "",
			userProfilePictureUrl = getUserInfoUseCase()?.profilePictureUrl ?: "",
			text = comment,
			date = getDateUseCase(showTime = false),
			numberOfReplies = 0,
			isReply = parentCommentId != null,
			parentCommentId = parentCommentId
		)
		
		val result = addCommentUseCase(contentId, cachedComment)
		_uiState.update {
			it.copy(
				isUploadingComment = false,
				commentUploadedSuccessfully = result,
				listOfCachedComments = if (result) it.listOfCachedComments.toMutableList()
					.apply { add(cachedComment) } else it.listOfCachedComments,
			)
		}
	}
	
	fun getComments(
		contentId: String,
		totalNumberOfCommentsExpected: Int
	) {
		_uiState.update {
			it.copy(
				commentUploadedSuccessfully = null,
				totalNumberOfComments = totalNumberOfCommentsExpected,
				openCommentsBottomSheet = true,
				loggedInUserEmail = getLoggedInUserEmailUseCase(),
				listOfCommentReplies = emptyList(),
				deletedComments = mutableListOf(),
				listOfCachedComments = mutableListOf()
			)
		}
		_comments.value = getCommentsUseCase(contentId).cachedIn(viewModelScope)
	}
	
	fun onCommentsBottomDialogClosed() {
		_uiState.update { it.copy(openCommentsBottomSheet = false) }
	}
	
	fun getRepliesToComment(commentId: String, pageNumber: Int) = viewModelScope.launch {
		_uiState.update { it.copy(isLoadingReplies = true) }
		val isLastPage = getCommentRepliesUseCase(commentId, pageNumber).first
		val listOfReplies = getCommentRepliesUseCase(commentId, pageNumber).second ?: emptyList()
		_uiState.update {
			it.copy(
				listOfCommentReplies = listOfReplies,
				isLoadingReplies = false,
				repliesPageNumber = pageNumber,
				canLoadMoreReplies = isLastPage
			)
		}
	}
	
	fun deleteComment(contentId: String, commentId: String) = viewModelScope.launch {
		_uiState.update { it.copy(commentDeletedSuccessfully = null) }
		val listOfDeletedComments = _uiState.value.deletedComments
		var totalNumberOfComments = _uiState.value.totalNumberOfComments
		
		val result = deleteCommentUseCase(commentId)
		if (result) {
			listOfDeletedComments.add(commentId)
			totalNumberOfComments -= 1
		}
		
		_uiState.update {
			it.copy(
				commentDeletedSuccessfully = result,
				deletedComments = listOfDeletedComments,
				totalNumberOfComments = totalNumberOfComments,
				mapOfContentsAndNewNumberOfComments = it.mapOfContentsAndNewNumberOfComments.toMutableMap()
					.apply {
						this[contentId] = totalNumberOfComments
					}
			)
		}
	}
	
	fun saveReply(originalCommentId: String, reply: String) = viewModelScope.launch {
		val saved = saveReplyToACommentUseCase(originalCommentId, reply)
		// do something with saved
	}
	
	fun saveScrollPosition(index: Int, offset: Int) {
		_uiState.update {
			it.copy(
				firstVisibleItemIndex = index,
				firstVisibleItemScrollOffset = offset
			)
		}
	}
	
	private fun mapContent(content: Content, adProvider: QuickReelsNativeAdProvider): Content {
		return if (content.isAd) {
			Advert(null)
		} else {
			content
		}
	}
	
	private suspend fun loadAds() {
		adProvider.loadAds()
	}
}