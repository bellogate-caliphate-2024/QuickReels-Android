package com.bellogatecaliphate.timeline.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.bellogatecaliphate.domain.comments.GetCommentRepliesUseCase
import com.bellogatecaliphate.domain.comments.GetCommentsUseCase
import com.bellogatecaliphate.domain.comments.SaveReplyToACommentUseCase
import com.bellogatecaliphate.domain.contents.GetContentsUseCase
import com.bellogatecaliphate.domain.contents.like.LikeContentUseCase
import com.bellogatecaliphate.nativeads.QuickReelsNativeAdLoader
import com.bellogatecaliphate.timeline.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeLineScreenViewModel @Inject constructor(
	private val getContentsUseCase: GetContentsUseCase,
	private val likeContentUseCase: LikeContentUseCase,
	private val getCommentsUseCase: GetCommentsUseCase,
	private val getCommentRepliesUseCase: GetCommentRepliesUseCase,
	private val saveReplyToACommentUseCase: SaveReplyToACommentUseCase,
	private val adLoader: QuickReelsNativeAdLoader
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	init {
		getContents()
	}
	
	private fun getContents() = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
		val response = getContentsUseCase().cachedIn(viewModelScope)
		_uiState.update { it.copy(listOfPaginatedContents = response, isLoading = false) }
	}
	
	fun likeContent(contentId: String, isLiked: Boolean) = viewModelScope.launch {
		likeContentUseCase(contentId, isLiked)
	}
	
	fun getComments(contentId: String, totalNumberOfCommentsExpected: Int) = viewModelScope.launch {
		_uiState.update { it.copy(openCommentsBottomSheet = true, isLoadingComments = true) }
		getCommentsUseCase(contentId).cachedIn(viewModelScope).collect { lazyPagingItems ->
			lazyPagingItems.loadState.refresh.collect { loadState ->
				when (loadState) {
					is LoadState.Loading    -> {
						// If still loading the first page, keep the loading state
						_uiState.update { it.copy(isLoadingComments = true) }
					}
					
					is LoadState.NotLoading -> {
						// Once the first page is loaded, set loading state to false
						_uiState.update {
							it.copy(
								totalNumberOfComments = totalNumberOfCommentsExpected,
								listOfPaginatedComments = lazyPagingItems,
								isLoadingComments = false
							)
						}
					}
					
					is LoadState.Error      -> {
						// Handle error loading the first page if necessary
						_uiState.update { it.copy(isLoadingComments = false) }
					}
				}
			}
		}
		
		_uiState.update {
			it.copy(
				totalNumberOfComments = totalNumberOfCommentsExpected,
				listOfPaginatedComments = response,
				isLoadingComments = false
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
	
	fun saveReply(originalCommentId: String, reply: String) = viewModelScope.launch {
		val saved = saveReplyToACommentUseCase(originalCommentId, reply)
		// do something with saved
	}
	
	fun getAdLoader() = adLoader
}