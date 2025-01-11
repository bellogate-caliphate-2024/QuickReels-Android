package com.bellogatecaliphate.create_post.ui.preview_post.video_preview

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
internal fun VideoPreview(modifier: Modifier, videoPath: String) {
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
		modifier = modifier.fillMaxWidth(),
		factory = {
			PlayerView(context).apply { player = exoPlayer }
		})
	
	DisposableEffect(Unit) {
		onDispose { exoPlayer.release() }
	}
}