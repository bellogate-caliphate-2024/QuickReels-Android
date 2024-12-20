package com.bellogatecaliphate.create_post.ui.preview_post

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.R
import com.bellogatecaliphate.create_post.ui.preview_post.model.PreviewPostUiState

@Composable
fun PreviewPostScreen(
	videoPath: String,
	caption: String?,
	isReadOnly: Boolean,
	onShowConfirmationBottomSheet: (post: Post) -> Unit,
	viewModel: PreviewPostScreenViewModel = hiltViewModel(),
) {
	val state = viewModel.state.collectAsStateWithLifecycle()
	PreviewPostScreen(videoPath, caption, isReadOnly, state.value, { videoCaption ->
		viewModel.validateVideoCaption(videoPath, videoCaption)
	}, onShowConfirmationBottomSheet)
}

@Composable
private fun PreviewPostScreen(
	videoPath: String,
	caption: String?,
	editable: Boolean,
	uiState: PreviewPostUiState,
	onSendButtonClicked: (videoCaption: String) -> Unit,
	onShowConfirmationBottomSheet: (post: Post) -> Unit
) {
	var text by rememberSaveable { mutableStateOf(caption ?: "") }
	
	Column(Modifier.fillMaxSize()) {
		VideoPreview(Modifier.weight(1f), videoPath)
		VideoCaptionSection(editable, text) { text = it }
		if (editable) {
			SendButton { onSendButtonClicked(text) }
		}
	}
	when {
		uiState.videoCaptionTextIsNotProvided -> {
			VideoCaptureNotProvidedPrompt()
		}
		
		uiState.showConfirmationBottomSheet   -> {
			if (uiState.post != null) {
				onShowConfirmationBottomSheet(uiState.post)
			}
		}
	}
}

@Composable
private fun VideoCaptureNotProvidedPrompt() {
	val context = LocalContext.current
	LaunchedEffect(key1 = Unit) {
		Toast.makeText(context, R.string.videoCaptionTextIsNotProvided, Toast.LENGTH_LONG)
			.show()
	}
}

@Composable
private fun VideoPreview(modifier: Modifier, videoPath: String) {
	val context = LocalContext.current
	val uri = Uri.parse(videoPath)
	val exoPlayer = remember {
		ExoPlayer.Builder(context).build().apply {
			setMediaItem(MediaItem.fromUri(uri))
			prepare()
			playWhenReady = true
		}
	}
	
	AndroidView(
		modifier = modifier,
		factory = {
			PlayerView(context).apply { player = exoPlayer }
		})
	
	DisposableEffect(Unit) {
		onDispose { exoPlayer.release() }
	}
}

@Composable
private fun VideoCaptionSection(
	isReadOnly: Boolean,
	descriptionText: String?,
	onValueChange: (String) -> Unit
) {
	OutlinedTextField(modifier = Modifier.fillMaxWidth(),
		value = descriptionText ?: "",
		readOnly = isReadOnly,
		onValueChange = onValueChange,
		label = { Text("Add a caption...") })
}

@Composable
private fun SendButton(onClick: () -> Unit) {
	TextButton(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
		Text("Send", fontSize = 20.sp)
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewPostPreview() {
	PreviewPostScreen("", "", true, { })
}
