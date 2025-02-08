package com.bellogatecaliphate.create_post.ui.create_post

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.model.UiState
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.UploadStatusCardHolder
import com.bellogatecaliphate.create_post.ui.create_post.util.activityLauncher
import com.bellogatecaliphate.create_post.util.getActivity
import com.bellogatecaliphate.create_post.util.getStorageManifestPermission
import com.bellogatecaliphate.create_post.util.video_trimer.utils.TrimVideo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun CreatePostScreen(
	viewModel: CreatePostScreenViewModel = hiltViewModel(),
	onPostReadyForPreview: (videoPath: String, videoCaption: String?, isReadOnly: Boolean) -> Unit = { _, _, _ -> },
	onPostClicked: (Post) -> Unit = {},
) {
	val context = LocalContext.current.getActivity()
	val videoTrimResultLauncher = activityLauncher(onPostReadyForPreview)
	CreatePostScreen(
		viewModel.state.collectAsStateWithLifecycle().value,
		viewModel::requestPermissionAndOpenGallery,
		onPostClicked,
	) { uri ->
		viewModel.resetGalleryState()
		TrimVideo.activity(uri)?.start(context, videoTrimResultLauncher)
	}
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CreatePostScreen(
	uiState: UiState,
	openGallery: () -> Unit,
	onPostClicked: (Post) -> Unit,
	onVideoFileSelected: (uri: String?) -> Unit,
) {
	val storagePermission = rememberPermissionState(getStorageManifestPermission())
	UploadStatusCardHolder(uiState.existingUploads, openGallery, onPostClicked)
	VideoFilePicker(
		uiState.requestStoragePermissionAndOpenGallery,
		storagePermission,
		storagePermission::launchPermissionRequest,
		onVideoFileSelected
	)
}

@PreviewScreenSizes
@Preview(showBackground = true)
@Composable
private fun Preview() {
	CreatePostScreen(UiState(), {}, {}, {})
}