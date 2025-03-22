package com.bellogatecaliphate.timeline.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.core.ui.ProgressBar
import com.bellogatecaliphate.core.ui.comments.CommentsBottomDialog
import com.bellogatecaliphate.nativeads.QuickReelsNativeAdLoader
import com.bellogatecaliphate.timeline.model.UiState
import com.bellogatecaliphate.timeline.ui.content.Contents

@Composable
fun TimeLineScreen(viewModel: TimeLineScreenViewModel = hiltViewModel()) {
	val uiState = viewModel.uiState.collectAsStateWithLifecycle()
	TimeLineScreen(
		uiState = uiState.value,
		adLoader = viewModel.getAdLoader(),
		onLikeButtonPressed = { contentId, isLiked ->
			viewModel.likeContent(contentId, isLiked)
		},
		onCommentButtonPressed = { contentId ->
			viewModel.getComments(contentId)
		},
		onCommentsBottomDialogClosed = {
			viewModel.onCommentsBottomDialogClosed()
		},
		onSaveReply = { originalCommentId, reply ->
			viewModel.saveReply(originalCommentId, reply)
		},
		onLoadReplies = { originalCommentId, pageNumber ->
			viewModel.getRepliesToComment(originalCommentId, pageNumber)
		}
	)
}

@Composable
private fun TimeLineScreen(
	uiState: UiState,
	adLoader: QuickReelsNativeAdLoader,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: (contentId: String) -> Unit,
	onCommentsBottomDialogClosed: () -> Unit = {},
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> }
) {
	Column {
		ProgressBar(uiState.isLoading)
		Contents(
			uiState.listOfContents.collectAsLazyPagingItems(),
			adLoader,
			onLikeButtonPressed,
			onCommentButtonPressed
		)
		CommentsBottomDialog(
			visible = uiState.openCommentsBottomSheet,
			isLoadingInitialComments = uiState.isLoadingComments,
			listOfComments = uiState.listOfComments.collectAsLazyPagingItems(),
			isLoadingReplies = uiState.isLoadingReplies,
			listOfReplies = uiState.listOfCommentReplies,
			repliesPageNumber = uiState.repliesPageNumber,
			canLoadMoreReplies = uiState.canLoadMoreReplies,
			onCommentsBottomDialogClosed = onCommentsBottomDialogClosed,
			onSaveReply = onSaveReply,
			onLoadReplies = onLoadReplies
		)
	}
}