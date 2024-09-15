package com.bellogatecaliphate.create_post.ui.preview_post

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun PreviewPost(videoPath : String = "" , onSendButtonClicked : () -> Unit = {}) {
	Column(Modifier.fillMaxSize()) {
		VideoPreview(Modifier.weight(1f) , videoPath)
		VideoDescriptionSection()
		SendButton(onSendButtonClicked)
	}
}

@Composable
private fun VideoPreview(modifier : Modifier , videoPath : String) {
	val context = LocalContext.current
	val uri = Uri.parse(videoPath)
	val exoPlayer = remember {
		ExoPlayer.Builder(context).build().apply {
			setMediaItem(MediaItem.fromUri(uri))
			prepare()
			playWhenReady = true
		}
	}
	
	DisposableEffect(
		AndroidView(
			modifier = modifier ,
			factory = {
				PlayerView(context).apply { player = exoPlayer }
			})
	) {
		onDispose { exoPlayer.release() }
	}
	
}

@Composable
private fun VideoDescriptionSection() {
	var text by rememberSaveable { mutableStateOf("") }
	OutlinedTextField(modifier = Modifier.fillMaxWidth() ,
		value = text ,
		onValueChange = { text = it } ,
		label = { Text("Add a caption...") })
}

@Composable
private fun SendButton(onClick : () -> Unit) {
	TextButton(onClick = onClick , modifier = Modifier.fillMaxWidth()) {
		Text("Send" , fontSize = 20.sp)
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewPostPreview() {
	PreviewPost()
}
