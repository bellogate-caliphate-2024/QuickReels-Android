package com.bellogatecaliphate.timeline.ui.content.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil3.compose.AsyncImage
import com.bellogatecaliphate.core.ui.ProgressBar

@Composable
internal fun VideoSection(modifier: Modifier, videoUrl: String, videoThumbnailUrl: String) {
	var isLoadingVideo by remember { mutableStateOf(false) }
	
	Column(modifier.background(Color.Black)) {
		ProgressBar(isLoadingVideo)
		VideoThumbnail(isLoadingVideo.not(), videoThumbnailUrl)
		VideoStreamer(
			modifier, videoUrl,
			isLoading = { isLoadingVideo = true },
			isReadyToPlay = { isLoadingVideo = false }
		)
	}
}

@Composable
private fun VideoThumbnail(visible: Boolean, videoThumbnailUrl: String) {
	if (visible.not()) return
	AsyncImage(
		model = videoThumbnailUrl,
		contentDescription = null,
		modifier = Modifier
			.fillMaxSize()
			.blur(radius = 10.dp)
	)
}

@Composable
private fun VideoStreamer(
	modifier: Modifier,
	videoUrl: String,
	isLoading: () -> Unit,
	isReadyToPlay: () -> Unit
) {
	val context = LocalContext.current
	val exoPlayer = remember {
		ExoPlayer.Builder(context).build().apply {
			setMediaItem(MediaItem.fromUri(videoUrl))
			prepare()
			playWhenReady = true
		}
	}
	
	exoPlayer.addListener(object : Player.Listener {
		override fun onPlaybackStateChanged(@Player.State state: Int) {
			when (state) {
				Player.STATE_READY -> {
					// The player is able to immediately play from its current position.
					// The player will be playing if getPlayWhenReady() is true, and paused otherwise.
					isReadyToPlay()
				}
				
				Player.STATE_BUFFERING -> {
					// The player is not able to immediately play the media, but is doing work toward being able to do so.
					// This state typically occurs when the player needs to buffer more data before playback can start.
					isLoading()
				}
				
				Player.STATE_IDLE -> {
					// The player is idle, meaning it holds only limited resources.The player must be prepared before it will play the media.
				}
				
				Player.STATE_ENDED -> { // The player has finished playing the media. }
				}
			}
		}
	})
	
	AndroidView(
		modifier = modifier.fillMaxWidth(),
		factory = {
			PlayerView(context).apply { player = exoPlayer }
		},
		update = { view ->
			view.setBackgroundColor(Color.Black.toArgb())
		}
	)
	
	DisposableEffect(Unit) {
		onDispose {
			exoPlayer.playWhenReady = false
			exoPlayer.release()
		}
	}
}