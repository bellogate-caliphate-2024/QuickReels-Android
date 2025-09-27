package com.bellogatecaliphate.account.ui.screens.view_and_edit_content

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.ui.screens.view_and_edit_content.captions.EditCaptionBottomSheet
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.ui.content.ContentListItem

@Composable
internal fun EditContentScreen(
	contentId: String,
	onClose: () -> Unit
) {
	val viewModel: ViewAndEditContentScreenViewModel = hiltViewModel()
	val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
	
	LaunchedEffect(contentId) { viewModel.getContent(contentId = contentId) }
	EditContentScreen(
		uiState = uiState,
		onClose = onClose,
		onLikeButtonPressed = { contentId, isLiked ->
			viewModel.likeContent(contentId, isLiked)
		},
		onRefresh = { viewModel.getContent(isRefreshing = true, contentId = contentId) },
		onDeleteContent = { viewModel.deleteContent(contentId = contentId) },
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditContentScreen(
	uiState: UiState,
	onClose: () -> Unit,
	onRefresh: () -> Unit,
	onDeleteContent: () -> Unit,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit = { _, _ -> },
	onCommentButtonPressed: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit = { _, _ -> },
	onOpenAccountDetails: (accountUserEmail: String) -> Unit = { _ -> },
) {
	var showEditCaption by remember { mutableStateOf(false) }
	val context = LocalContext.current
	LaunchedEffect(uiState.deleteContentSuccess) {
		val contentDeleteStatus = uiState.deleteContentSuccess
		if (contentDeleteStatus != null) {
			val message = context.getString(getDeleteTextMessage(contentDeleteStatus))
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
			if (contentDeleteStatus) {
				onClose()
			}
		}
	}
	
	PullToRefreshBox(
		isRefreshing = uiState.isRefreshing,
		onRefresh = onRefresh,
		modifier = Modifier.fillMaxSize()
	) {
		Column(
			modifier = Modifier
				.background(color = Color.White)
				.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			when {
				uiState.isLoadingInitialContent -> QuickReelsCircularProgressBar()
				uiState.errorLoadingContent     -> Text(stringResource(R.string.error_loading_content))
				uiState.content != null         -> ContentListItem(
					modifier = Modifier.fillMaxSize(),
					content = uiState.content,
					listOfLikedContents = uiState.likedContents,
					showBackButton = true,
					contentBelongsToLoggedInUser = uiState.contentBelongsToLoggedInUser,
					deleteContentInProgress = uiState.deleteContentInProgress,
					onLikeButtonPressed = onLikeButtonPressed,
					onCommentButtonPressed = onCommentButtonPressed,
					onOpenAccountDetails = onOpenAccountDetails,
					onDeleteContent = onDeleteContent,
					onEditCaptionClicked = { showEditCaption = true },
					onClose = onClose
				)
			}
			EditCaptionBottomSheet(
				show = showEditCaption,
				caption = uiState.content?.caption ?: "",
				onSavedSuccessfully = {
					showEditCaption = false
					onClose()
				},
				onDismiss = { showEditCaption = false }
			)
		}
	}
}

private fun getDeleteTextMessage(success: Boolean): Int {
	return if (success) R.string.deleted else R.string.delete_failed
}