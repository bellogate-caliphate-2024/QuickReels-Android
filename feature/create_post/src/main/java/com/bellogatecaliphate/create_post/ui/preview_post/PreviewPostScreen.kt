package com.bellogatecaliphate.create_post.ui.preview_post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.ui.confirm_post.UploadPostConfirmationDialog
import com.bellogatecaliphate.create_post.ui.preview_post.bottom_section.BottomSection
import com.bellogatecaliphate.create_post.ui.preview_post.model.PreviewPostUiState
import com.bellogatecaliphate.create_post.ui.preview_post.util.VideoCaptionNotProvidedPrompt
import com.bellogatecaliphate.create_post.ui.preview_post.video_caption_section.VideoCaptionSection
import com.bellogatecaliphate.create_post.ui.preview_post.video_preview.VideoPreview

@Composable
fun PreviewPostScreen(
	videoPath: String,
	caption: String?,
	isReadOnly: Boolean,
	viewModel: PreviewPostScreenViewModel = hiltViewModel(),
	dismiss: () -> Unit
) {
	val state = viewModel.state.collectAsStateWithLifecycle()
	PreviewPostScreen(
		videoPath = videoPath,
		caption = caption,
		isReadOnly = isReadOnly,
		uiState = state.value,
		onSendButtonClicked = { videoCaption ->
			viewModel.validateVideoCaption(videoPath, videoCaption)
		},
		onConfirmationButtonClicked = { post ->
			viewModel.enQueuePostForUpload(post)
			dismiss()
		}
	)
}

@Composable
private fun PreviewPostScreen(
	videoPath: String,
	caption: String?,
	isReadOnly: Boolean,
	uiState: PreviewPostUiState,
	onSendButtonClicked: (videoCaption: String) -> Unit,
	onConfirmationButtonClicked: (Post) -> Unit
) {
	var text by rememberSaveable { mutableStateOf(caption ?: "") }
	
	Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
		VideoPreview(Modifier.weight(1f), videoPath)
		VideoCaptionSection(isReadOnly, text) { text = it }
		BottomSection(text, isReadOnly, uiState.isLoading, onSendButtonClicked)
	}
	when {
		uiState.videoCaptionTextIsNotProvided -> {
			VideoCaptionNotProvidedPrompt()
		}
		
		uiState.showConfirmationBottomSheet   -> {
			UploadPostConfirmationDialog(uiState.post, onConfirmationButtonClicked)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewPostPreview() {
	PreviewPostScreen("", "", false, PreviewPostUiState(), {}, {})
}
