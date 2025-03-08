package com.bellogatecaliphate.timeline.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.nativeads.QuickReelsNativeAd
import com.bellogatecaliphate.timeline.databinding.QuickReelsNativeAdViewBinding
import com.bellogatecaliphate.timeline.model.UiState

@Composable
fun TimeLineScreen(viewModel: TimeLineScreenViewModel = hiltViewModel()) {
	val uiState = viewModel.uiState.collectAsStateWithLifecycle()
	TimeLineScreen(
		uiState = uiState.value,
		onLikeButtonPressed = { contentId, isLiked ->
			viewModel.likeContent(contentId, isLiked)
		},
		onCommentButtonPressed = { contentId ->
			viewModel.getComments(contentId)
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
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: (contentId: String) -> Unit,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> }
) {
	AndroidViewBinding(QuickReelsNativeAdViewBinding::inflate) {
		QuickReelsNativeAd(context = root.context) { ad ->
			adTitle.text = ad.headline
			adView.headlineView = adTitle
			adView.setNativeAd(ad)
		}.x()
	}
	/*Column {
		ProgressBar(uiState.isLoading)
		Contents(
			uiState.listOfContents.collectAsLazyPagingItems(),
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
			onSaveReply = onSaveReply,
			onLoadReplies = onLoadReplies
		)
	}*/
}