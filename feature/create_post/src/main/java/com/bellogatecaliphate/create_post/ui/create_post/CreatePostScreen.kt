package com.bellogatecaliphate.create_post.ui.create_post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_200DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_TEXT_SIZE_20
import com.bellogatecaliphate.create_post.R
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
		onCancelUploadClicked = viewModel::cancelPostUpload,
		onCloseUploadStatus = viewModel::cancelPostUpload
	)
}

@Composable
private fun CreatePostScreen(
	uiState: UiState,
	openGallery: () -> Unit,
	onPostClicked: (Post) -> Unit,
	onVideoFileSelected: (uri: String?) -> Unit,
	onStoragePermissionRationalDialogClosed: () -> Unit = {},
	onCancelUploadClicked: (Post) -> Unit,
	onCloseUploadStatus: (Post) -> Unit
) {
	Column(
		verticalArrangement = Arrangement.Bottom,
		modifier = Modifier
			.fillMaxSize()
			.background(color = Color.Black)
	) {
		DefaultContent(
			visible = uiState.existingUploads.isEmpty(),
			openGallery = openGallery
		)
		UploadStatusScreen(
			modifier = Modifier.weight(1f),
			visible = uiState.existingUploads.isEmpty().not(),
			numberOfUploadsInProgressToDisplay = 1, // We only want to track one upload
			uploadsInProgress = uiState.existingUploads,
			onPostClicked = onPostClicked,
			onCancelUploadClicked = onCancelUploadClicked,
			onCloseUploadStatus = onCloseUploadStatus
		)
		SelectVideoButton(openGallery)
	}
	
	VideoFilePicker(
		uiState.requestStoragePermissionAndOpenGallery,
		onVideoFileSelected,
		onStoragePermissionRationalDialogClosed
	)
}

@Composable
private fun DefaultContent(
	visible: Boolean,
	openGallery: () -> Unit
) {
	if (visible.not()) return
	
	Column(
		modifier = Modifier
			.background(Color.White)
			.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painterResource(R.drawable.quickreels_inapp_icon),
			contentDescription = "",
			Modifier.size(PLACEHOLDER_200DP)
		)
		Spacer(modifier = Modifier.height(PLACEHOLDER_24DP))
		Text(stringResource(R.string.create_post_primary_message))
		Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
		Text(
			stringResource(R.string.create_post_secondary_message),
			textAlign = TextAlign.Center
		)
		Spacer(modifier = Modifier.height(PLACEHOLDER_24DP))
		Button(
			colors = ButtonDefaults.filledTonalButtonColors(
				containerColor = colorResource(com.bellogatecaliphate.core.R.color.quickreels_purple),
				contentColor = Color.White
			),
			onClick = openGallery
		) {
			Text(stringResource(R.string.select_video), fontSize = PLACEHOLDER_TEXT_SIZE_20)
		}
	}
}

@PreviewScreenSizes
@Preview(showBackground = true)
@Composable
private fun Preview() {
	CreatePostScreen(UiState(), {}, {}, {}, {}, {}, {})
}