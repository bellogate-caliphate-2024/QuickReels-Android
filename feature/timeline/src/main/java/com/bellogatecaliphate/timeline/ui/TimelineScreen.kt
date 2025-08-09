package com.bellogatecaliphate.timeline.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.bannerads.QuickReelsBannerAd
import com.bellogatecaliphate.core.model.ads.Ads
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.ui.comments.CommentsBottomDialog
import com.bellogatecaliphate.timeline.model.UiState
import com.bellogatecaliphate.timeline.ui.content.ContentsList
import com.example.interstitialads.QuickReelsInterstitialAd

@Composable
fun TimeLineScreen(
	viewModel: TimeLineScreenViewModel = hiltViewModel(),
	onOpenAccountDetails: (accountUserEmail: String) -> Unit
) {
	val context = LocalContext.current
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
		onDeleteComment = { commentId ->
			viewModel.deleteComment(commentId)
		},
		onSaveScrollPosition = { index, offset ->
			viewModel.saveScrollPosition(index, offset)
		},
		onOpenAccountDetails = onOpenAccountDetails
	)
	
	LaunchedEffect(Unit) { QuickReelsInterstitialAd.loadAds(context) }
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
	onDeleteComment: (commentId: String) -> Unit = { _ -> },
	onSaveScrollPosition: (index: Int, offset: Int) -> Unit,
	onOpenAccountDetails: (accountUserEmail: String) -> Unit
) {
	val context = LocalContext.current
	val listOfContents = uiState.listOfPaginatedContents.collectAsLazyPagingItems()
	val isLoadingInitialListItems = listOfContents.loadState.refresh is LoadState.Loading
	val errorLoadingInitialListItems = listOfContents.loadState.refresh is LoadState.Error
	var showInterstitialAd by remember { mutableStateOf(false) }
	
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		QuickReelsCircularProgressBar(show = isLoadingInitialListItems)
		if (isLoadingInitialListItems.not()) {
			ContentsList(
				list = listOfContents,
				advert = uiState.adVert,
				firstVisibleItemIndex = uiState.firstVisibleItemIndex ?: 0,
				firstVisibleItemScrollOffset = uiState.firstVisibleItemScrollOffset ?: 0,
				onAdRequest = onAdRequest,
				onLikeButtonPressed = onLikeButtonPressed,
				onCommentButtonPressed = onCommentButtonPressed,
				onSaveScrollPosition = onSaveScrollPosition,
				onOpenAccountDetails = onOpenAccountDetails,
				onDownloadClicked = { showInterstitialAd = true }
			)
			CommentsBottomDialog(
				visible = uiState.openCommentsBottomSheet,
				loggedInUserEmail = uiState.loggedInUserEmail,
				totalNumberOfCommentsExpected = uiState.totalNumberOfComments,
				listOfComments = uiState.listOfPaginatedComments.collectAsLazyPagingItems(),
				commentDeletedSuccessfully = uiState.commentDeletedSuccessfully,
				listOfDeletedComments = uiState.deletedComments,
				isLoadingReplies = uiState.isLoadingReplies,
				listOfReplies = uiState.listOfCommentReplies,
				repliesPageNumber = uiState.repliesPageNumber,
				canLoadMoreReplies = uiState.canLoadMoreReplies,
				onCommentsBottomDialogClosed = onCommentsBottomDialogClosed,
				onSaveReply = onSaveReply,
				onLoadReplies = onLoadReplies,
				onDeleteComment = onDeleteComment,
				advertContainer = { BannerAd() }
			)
			LaunchedEffect(showInterstitialAd) {
				QuickReelsInterstitialAd.showAdOrSkip(
					show = showInterstitialAd,
					context = context,
					onAdSkipped = { showInterstitialAd = false },
					onAdDismissed = { showInterstitialAd = false }
				)
			}
		}
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