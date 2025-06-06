package com.bellogatecaliphate.create_post.ui.create_post.upload_status.upload_status_screen_types

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_150DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_200DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_TEXT_SIZE_30
import com.bellogatecaliphate.create_post.R
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.FailedIcon
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.SuccessIcon
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.ThumbnailPreview
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.util.provideUploadStatusMessage
import com.bellogatecaliphate.create_post.ui.delete_post.DeletePostConfirmationDialog

@Composable
internal fun SingleUploadStatusScreen(
	uploadInProgress: Post,
	onPostClicked: (Post) -> Unit,
	onCancelUploadClicked: (Post) -> Unit,
	onCloseUploadStatus: (Post) -> Unit
) {
	var showCancelUploadBottomSheetDialog by remember { mutableStateOf(false) }
	Scaffold(
		topBar = {
			SingleUploadStatusScreenTopBar(uploadInProgress, onCloseUploadStatus) {
				showCancelUploadBottomSheetDialog = true
			}
		}
	) { innerPadding ->
		SingleUploadStatusScreenContent(
			modifier = Modifier.padding(innerPadding),
			uploadInProgress,
			onPostClicked
		)
	}
	DeletePostConfirmationDialog(
		isVisible = showCancelUploadBottomSheetDialog,
		onConfirmationGiven = {
			showCancelUploadBottomSheetDialog = false
			onCancelUploadClicked(uploadInProgress)
		},
		onDismiss = { showCancelUploadBottomSheetDialog = false }
	)
}

@Composable
private fun SingleUploadStatusScreenTopBar(
	uploadInProgress: Post,
	onCloseUploadStatus: (Post) -> Unit,
	onCancelUploadClicked: () -> Unit
) {
	val uploadIsInProgress = uploadInProgress.isUploading
	Box(
		modifier = Modifier
			.background(Color.White)
			.padding(PLACEHOLDER_16DP)
			.fillMaxWidth(),
		contentAlignment = Alignment.CenterEnd
	) {
		Image(
			painterResource(R.drawable.ic_cancel),
			contentDescription = "",
			modifier = Modifier.clickable(onClick = {
				if (uploadIsInProgress) {
					onCancelUploadClicked()
				} else {
					onCloseUploadStatus(uploadInProgress)
				}
			}),
		)
	}
}

@Composable
private fun SingleUploadStatusScreenContent(
	modifier: Modifier,
	uploadInProgress: Post,
	onPostClicked: (Post) -> Unit
) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.background(color = Color.White),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Box(
			modifier = Modifier.clickable(onClick = { onPostClicked(uploadInProgress) }),
			contentAlignment = Alignment.Center
		) {
			if (uploadInProgress.isUploading) {
				QuickReelsCircularProgressBar(PLACEHOLDER_200DP)
				ThumbnailPreview(uploadInProgress.thumbnailFilePath, PLACEHOLDER_150DP)
			}
			if (uploadInProgress.isUploaded) {
				SuccessIcon(PLACEHOLDER_200DP)
			}
			if (uploadInProgress.isUploadFailed) {
				FailedIcon(PLACEHOLDER_200DP)
			}
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
		Text(
			provideUploadStatusMessage(LocalContext.current, uploadInProgress),
			fontSize = PLACEHOLDER_TEXT_SIZE_30
		)
	}
}