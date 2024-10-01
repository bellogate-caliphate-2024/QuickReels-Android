package com.bellogatecaliphate.create_post.ui.create_post

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.model.UiState
import com.bellogatecaliphate.create_post.ui.create_post.upload_status.UploadStatusCard
import com.bellogatecaliphate.create_post.util.getActivity
import com.bellogatecaliphate.create_post.util.getStorageManifestPermission
import com.bellogatecaliphate.create_post.util.video_trimer.utils.TrimVideo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun CreatePostScreen(
	viewModel: CreatePostScreenViewModel = hiltViewModel(),
	onPostReadyForPreview: (videoPath: String) -> Unit = {},
	onPostClicked: (Post) -> Unit = {},
) {
	val context = LocalContext.current.getActivity()
	val videoTrimResultLauncher = activityLauncher(onPostReadyForPreview)
	CreatePostScreen(
		viewModel.state.collectAsStateWithLifecycle().value,
		viewModel::requestPermissionAndOpenGallery,
		onPostClicked,
	) { data -> TrimVideo.activity(data).start(context, videoTrimResultLauncher) }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CreatePostScreen(
	uiState: UiState,
	openGallery: () -> Unit,
	onPostClicked: (Post) -> Unit,
	onVideoFileSelected: (String) -> Unit,
) {
	val storagePermission = rememberPermissionState(getStorageManifestPermission())
	MainScreen(uiState.uploadsInProgress, openGallery, onPostClicked)
	if (uiState.requestStoragePermissionAndOpenGallery) {
		VideoFilePicker(
			storagePermission,
			storagePermission::launchPermissionRequest,
			onVideoFileSelected
		)
	}
}

@Composable
private fun MainScreen(
	uploadsInProgress: List<Post>,
	openGallery: () -> Unit,
	onPostClicked: (Post) -> Unit
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = Color.Black)
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(color = Color.White)
				.padding(10.dp)
				.align(Alignment.TopCenter)
		) {
			Column {
				uploadsInProgress.forEach { post ->
					UploadStatusCard(
						post.caption,
						post.time,
						post.uploadProgressPercentage,
						post.thumbnailBase64String
					) { onPostClicked(post) }
				}
			}
		}
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(color = Color.White)
				.padding(10.dp)
				.align(Alignment.BottomCenter)
		) {
			TextButton(onClick = openGallery, modifier = Modifier.align(Alignment.BottomCenter)) {
				Text("Select Video", fontSize = 20.sp)
			}
		}
	}
}

@Composable
private fun activityLauncher(onPostReadyForPreview: (videoPath: String) -> Unit): ManagedActivityResultLauncher<Intent, ActivityResult> {
	return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
		val data = result.data
		if (result.resultCode == Activity.RESULT_OK && data != null) {
			onPostReadyForPreview(TrimVideo.getTrimmedVideoPath(data))
		}
	}
}

@PreviewScreenSizes
@Preview(showBackground = true)
@Composable
private fun Preview() {
	CreatePostScreen {}
}