package com.bellogatecaliphate.create_post.ui.create_post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.model.UiState
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.UploadStatusScreen
import com.bellogatecaliphate.create_post.ui.create_post.util.activityLauncher
import com.bellogatecaliphate.create_post.util.getActivity
import com.bellogatecaliphate.create_post.util.video_trimer.utils.TrimVideo

@Composable
fun CreatePostScreen(
	viewModel: CreatePostScreenViewModel = hiltViewModel(),
	onPostReadyForPreview: (videoPath: String, videoCaption: String?, isReadOnly: Boolean) -> Unit = { _, _, _ -> },
	onPostClicked: (Post) -> Unit = {},
) {
	val context = LocalContext.current.getActivity()
	val videoTrimResultLauncher = activityLauncher(onPostReadyForPreview)
	
	CreatePostScreen(
		uiState = viewModel.state.collectAsStateWithLifecycle().value,
		openGallery = viewModel::requestPermissionAndOpenGallery,
		onPostClicked = onPostClicked,
		onVideoFileSelected = { uri ->
			viewModel.resetGalleryState()
			TrimVideo.activity(uri)?.start(context, videoTrimResultLauncher)
		},
		onStoragePermissionRationalDialogClosed = { viewModel.resetGalleryState() },
		onCancelUploadClicked = viewModel::cancelUpload
	)
}

@Composable
private fun CreatePostScreen(
	uiState: UiState,
	openGallery: () -> Unit,
	onPostClicked: (Post) -> Unit,
	onVideoFileSelected: (uri: String?) -> Unit,
	onStoragePermissionRationalDialogClosed: () -> Unit = {},
	onCancelUploadClicked: (Post) -> Unit
) {
	Column(
		verticalArrangement = Arrangement.Bottom,
		modifier = Modifier
			.fillMaxSize()
			.background(color = Color.Black)
	) {
		UploadStatusScreen(
			modifier = Modifier.weight(1f),
			numberOfUploadsInProgressToDisplay = 1, // We only want to track one upload
			uploadsInProgress = uiState.existingUploads,
			onPostClicked = onPostClicked,
			onCancelUploadClicked = onCancelUploadClicked
		)
		SelectVideoButton(openGallery)
	}
	
	VideoFilePicker(
		uiState.requestStoragePermissionAndOpenGallery,
		onVideoFileSelected,
		onStoragePermissionRationalDialogClosed
	)
}

@PreviewScreenSizes
@Preview(showBackground = true)
@Composable
private fun Preview() {
	CreatePostScreen(UiState(), {}, {}, {}, {}, {})
}