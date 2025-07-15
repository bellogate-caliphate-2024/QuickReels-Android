package com.bellogatecaliphate.timeline.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.bannerads.QuickReelsBannerAd
import com.bellogatecaliphate.core.model.ads.Ads
import com.bellogatecaliphate.core.ui.ProgressBar
import com.bellogatecaliphate.core.ui.comments.CommentsBottomDialog
import com.bellogatecaliphate.timeline.model.UiState
import com.bellogatecaliphate.timeline.ui.content.ContentsList

@Composable
fun TimeLineScreen(
	viewModel: TimeLineScreenViewModel = hiltViewModel(),
	onOpenAccountDetails: (accountUserEmail: String) -> Unit
) {
	val uiState = viewModel.uiState.collectAsStateWithLifecycle()
	TimeLineScreen(
		uiState = uiState.value,
		onAdRequest = {
			viewModel.onAdRequest()
		},
		onLikeButtonPressed = { contentId, isLiked ->
			viewModel.likeContent(contentId, isLiked)
		},
		onCommentButtonPressed = { contentId, totalNumberOfCommentsExpected ->
			viewModel.getComments(contentId, totalNumberOfCommentsExpected)
		},
		onCommentsBottomDialogClosed = {
			viewModel.onCommentsBottomDialogClosed()
		},
		onSaveReply = { originalCommentId, reply ->
			viewModel.saveReply(originalCommentId, reply)
		},
		onLoadReplies = { originalCommentId, pageNumber ->
			viewModel.getRepliesToComment(originalCommentId, pageNumber)
		},
		onSaveScrollPosition = { index, offset ->
			viewModel.saveScrollPosition(index, offset)
		},
		onOpenAccountDetails = onOpenAccountDetails
	)
}

@Composable
private fun TimeLineScreen(
	uiState: UiState,
	onAdRequest: () -> Unit,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit,
	onCommentsBottomDialogClosed: () -> Unit = {},
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> },
	onSaveScrollPosition: (index: Int, offset: Int) -> Unit,
	onOpenAccountDetails: (accountUserEmail: String) -> Unit
) {
	Column {
		ProgressBar(uiState.isLoading)
		ContentsList(
			list = uiState.listOfPaginatedContents.collectAsLazyPagingItems(),
			advert = uiState.adVert,
			firstVisibleItemIndex = uiState.firstVisibleItemIndex ?: 0,
			firstVisibleItemScrollOffset = uiState.firstVisibleItemScrollOffset ?: 0,
			onAdRequest = onAdRequest,
			onLikeButtonPressed = onLikeButtonPressed,
			onCommentButtonPressed = onCommentButtonPressed,
			onSaveScrollPosition = onSaveScrollPosition,
			onOpenAccountDetails = onOpenAccountDetails
		)
		CommentsBottomDialog(
			visible = uiState.openCommentsBottomSheet,
			totalNumberOfCommentsExpected = uiState.totalNumberOfComments,
			listOfComments = uiState.listOfPaginatedComments.collectAsLazyPagingItems(),
			isLoadingReplies = uiState.isLoadingReplies,
			listOfReplies = uiState.listOfCommentReplies,
			repliesPageNumber = uiState.repliesPageNumber,
			canLoadMoreReplies = uiState.canLoadMoreReplies,
			onCommentsBottomDialogClosed = onCommentsBottomDialogClosed,
			onSaveReply = onSaveReply,
			onLoadReplies = onLoadReplies,
			footer = { BannerAd() }
		)
	}
}

@Composable
private fun BannerAd() {
	Column {
		Spacer(modifier = Modifier.height(8.dp))
		QuickReelsBannerAd(Modifier.fillMaxWidth(), Ads.BannerAds.CommentSectionBannerAd)
		Spacer(modifier = Modifier.height(8.dp))
	}
}