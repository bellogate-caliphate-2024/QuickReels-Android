package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.upload_status_screen_types.MultipleUploadsStatusScreen
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.upload_status_screen_types.SingleUploadStatusScreen

@Composable
internal fun UploadStatusScreen(
	modifier: Modifier,
	numberOfUploadsInProgressToDisplay: Int,
	uploadsInProgress: List<Post>,
	onPostClicked: (Post) -> Unit,
	onCancelUploadClicked: (Post) -> Unit,
	onCloseUploadStatus: (Post) -> Unit
) {
	if (uploadsInProgress.isEmpty()) return
	Content(
		modifier,
		numberOfUploadsInProgressToDisplay,
		uploadsInProgress,
		onPostClicked,
		onCancelUploadClicked,
		onCloseUploadStatus
	)
}

@Composable
private fun Content(
	modifier: Modifier,
	numberOfUploadsInProgressToDisplay: Int,
	uploadsInProgress: List<Post>,
	onPostClicked: (Post) -> Unit,
	onCancelUploadClicked: (Post) -> Unit,
	onCloseUploadStatus: (Post) -> Unit
) {
	if (numberOfUploadsInProgressToDisplay == 1) {
		SingleUploadStatusScreen(
			uploadInProgress = uploadsInProgress.first(),
			onPostClicked = onPostClicked,
			onCancelUploadClicked = onCancelUploadClicked,
			onCloseUploadStatus = onCloseUploadStatus
		)
	} else {
		MultipleUploadsStatusScreen(modifier, uploadsInProgress, onPostClicked)
	}
}