package com.bellogatecaliphate.timeline.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.bellogatecaliphate.core.model.dto.Advert
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.domain.comments.DeleteCommentUseCase
import com.bellogatecaliphate.domain.comments.GetCommentRepliesUseCase
import com.bellogatecaliphate.domain.comments.GetCommentsUseCase
import com.bellogatecaliphate.domain.comments.SaveReplyToACommentUseCase
import com.bellogatecaliphate.domain.contents.GetContentsUseCase
import com.bellogatecaliphate.domain.contents.like.LikeContentUseCase
import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
import com.bellogatecaliphate.nativeads.QuickReelsAdProvider
import com.bellogatecaliphate.timeline.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeLineScreenViewModel @Inject constructor(
	private val getLoggedInUserEmailUseCase: GetLoggedInUserEmailUseCase,
	private val getContentsUseCase: GetContentsUseCase,
	private val likeContentUseCase: LikeContentUseCase,
	private val getCommentsUseCase: GetCommentsUseCase,
	private val getCommentRepliesUseCase: GetCommentRepliesUseCase,
	private val saveReplyToACommentUseCase: SaveReplyToACommentUseCase,
	private val deleteCommentUseCase: DeleteCommentUseCase,
	private val adProvider: QuickReelsAdProvider
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	init {
		viewModelScope.launch {
			async { loadAds() }
			async { getContents() }
		}
	}
	
	fun onAdRequest() = viewModelScope.launch {
		val latestAd = adProvider.getNextAd()
		_uiState.update { it.copy(adVert = Advert(latestAd)) }
	}
	
	fun likeContent(contentId: String, isLiked: Boolean) = viewModelScope.launch {
		likeContentUseCase(contentId, isLiked)
	}
	
	fun getComments(contentId: String, totalNumberOfCommentsExpected: Int) = viewModelScope.launch {
		_uiState.update {
			it.copy(
				openCommentsBottomSheet = true,
				loggedInUserEmail = getLoggedInUserEmailUseCase()
			)
		}
		val response = getCommentsUseCase(contentId).cachedIn(viewModelScope)
		_uiState.update {
			it.copy(
				totalNumberOfComments = totalNumberOfCommentsExpected,
				listOfPaginatedComments = response
			)
		}
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
	
	fun deleteComment(commentId: String) = viewModelScope.launch {
		val result = deleteCommentUseCase(commentId)
		_uiState.update {
			it.copy(commentDeletedSuccessfully = result)
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
	
	private fun getContents() {
		_uiState.update { it.copy(isLoading = true) }
		val response = getContentsUseCase().cachedIn(viewModelScope).map {
			it.map { content -> mapContent(content, adProvider) }
		}
		_uiState.update { it.copy(listOfPaginatedContents = response, isLoading = false) }
	}
	
	private fun mapContent(content: Content, adProvider: QuickReelsAdProvider): Content {
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