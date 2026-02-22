package com.bellogatecaliphate.create_post.ui.create_post

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.arthenica.ffmpegkit.FFmpegKit
import com.bellogatecaliphate.create_post.R
import com.bellogatecaliphate.create_post.util.getActivity
import com.bellogatecaliphate.create_post.util.video_trimer.ui.seekbar.widgets.CrystalRangeSeekbar
import com.bellogatecaliphate.create_post.util.video_trimer.ui.seekbar.widgets.CrystalSeekbar
import com.bellogatecaliphate.create_post.util.video_trimer.utils.TrimmerUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.gowtham.library.utils.FileUtilKt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoTrimmerScreen(
	videoUri: String,
	onTrimFinished: (String) -> Unit,
	onBack: () -> Unit
) {
	val context = LocalContext.current
	val activity = remember(context) { context.getActivity() as? Activity }
	
	val player = remember(context) {
		ExoPlayer.Builder(context).build().apply {
			val audioAttributes = AudioAttributes.Builder()
				.setUsage(C.USAGE_MEDIA)
				.setContentType(C.CONTENT_TYPE_MOVIE)
				.build()
			setAudioAttributes(audioAttributes, true)
		}
	}
	
	var totalDuration by remember { mutableLongStateOf(0L) }
	var lastMinValue by remember { mutableLongStateOf(0L) }
	var lastMaxValue by remember { mutableLongStateOf(0L) }
	var isVideoEnded by remember { mutableStateOf(false) }
	var isActuallyPlaying by remember { mutableStateOf(false) }
	var isProcessing by remember { mutableStateOf(false) }
	var currentPlaybackPosition by remember { mutableLongStateOf(0L) }
	var resolvedPath by remember { mutableStateOf<String?>(null) }
	
	// Use rememberUpdatedState to ensure the latest values are used in callbacks
	val currentLastMinValue by rememberUpdatedState(lastMinValue)
	val currentLastMaxValue by rememberUpdatedState(lastMaxValue)
	val currentIsVideoEnded by rememberUpdatedState(isVideoEnded)
	val currentTotalDuration by rememberUpdatedState(totalDuration)
	
	val onPlayPauseClick = remember(player) {
		{
			if (currentIsVideoEnded) {
				player.seekTo(currentLastMinValue * 1000)
				player.playWhenReady = true
			} else {
				val currentPos = player.currentPosition / 1000
				if (currentTotalDuration > 0 && currentPos >= currentLastMaxValue) {
					player.seekTo(currentLastMinValue * 1000)
					player.playWhenReady = true
				} else {
					player.playWhenReady = ! player.playWhenReady
				}
			}
		}
	}
	
	LaunchedEffect(videoUri) {
		activity?.let { act ->
			val path = withContext(Dispatchers.IO) {
				FileUtilKt.getValidatedFileUri(act, Uri.parse(videoUri))
			}
			resolvedPath = path
			
			if (path != null) {
				val resolvedUri = Uri.parse(path)
				val mediaItem = MediaItem.fromUri(resolvedUri)
				player.setMediaItem(mediaItem)
				player.prepare()
				player.playWhenReady = true
				
				val duration = withContext(Dispatchers.IO) {
					try {
						TrimmerUtils.getDuration(act, resolvedUri)
					}
					catch (e: Exception) {
						0L
					}
				}
				if (duration > 0) {
					totalDuration = duration
					lastMaxValue = duration
				} else {
					Toast.makeText(act, "Video lenght is too short", Toast.LENGTH_SHORT).show()
				}
			} else {
				Toast.makeText(act, "Error loading video", Toast.LENGTH_SHORT).show()
			}
		}
	}
	
	// Loop to keep playback within trim range and update seeker position
	LaunchedEffect(isActuallyPlaying, lastMaxValue, lastMinValue) {
		if (isActuallyPlaying && lastMaxValue > 0) {
			while (isActive) {
				delay(100)
				val currentPosMs = player.currentPosition
				val currentPosSec = currentPosMs / 1000
				currentPlaybackPosition = currentPosSec
				
				if (currentPosSec >= lastMaxValue) {
					player.playWhenReady = false
					player.seekTo(lastMinValue * 1000)
					currentPlaybackPosition = lastMinValue
					break
				}
			}
		}
	}
	
	DisposableEffect(player) {
		val listener = object : Player.Listener {
			override fun onIsPlayingChanged(isPlaying: Boolean) {
				isActuallyPlaying = isPlaying
			}
			
			override fun onPlaybackStateChanged(playbackState: Int) {
				if (playbackState == Player.STATE_ENDED) {
					isVideoEnded = true
				} else if (playbackState == Player.STATE_READY) {
					isVideoEnded = false
				}
			}
			
			override fun onPlayerError(error: PlaybackException) {}
		}
		player.addListener(listener)
		onDispose {
			player.removeListener(listener)
			player.release()
			FFmpegKit.cancel()
		}
	}
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text("Trim Video", color = Color.White) },
				navigationIcon = {
					IconButton(onClick = onBack) {
						Icon(
							Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = "Back",
							tint = Color.White
						)
					}
				},
				actions = {
					IconButton(
						enabled = ! isProcessing && resolvedPath != null,
						onClick = {
							activity?.let { act ->
								isProcessing = true
								trimVideo(
									context = act,
									filePath = resolvedPath !!,
									lastMinValue = lastMinValue,
									lastMaxValue = lastMaxValue,
									onFinish = { path ->
										isProcessing = false
										onTrimFinished(path)
									},
									onError = {
										isProcessing = false
										Toast.makeText(act, "Failed to trim", Toast.LENGTH_SHORT)
											.show()
									}
								)
							}
						}) {
						Icon(Icons.Default.Check, contentDescription = "Done", tint = Color.White)
					}
				},
				colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF212121))
			)
		}
	) { padding ->
		Box(
			modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF303030))
		) {
			Column(modifier = Modifier.fillMaxSize()) {
				Box(
					modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onPlayPauseClick
                        ),
					contentAlignment = Alignment.Center
				) {
					AndroidView(
						factory = { ctx ->
							StyledPlayerView(ctx).apply {
								this.player = player
								useController = false
								resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
							}
						},
						update = { view ->
							view.player = player
						},
						modifier = Modifier.fillMaxSize()
					)
					
					if (! isActuallyPlaying) {
						Icon(
							painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_video_play_lib),
							contentDescription = "Play",
							modifier = Modifier.size(64.dp),
							tint = Color.White
						)
					}
				}
				
				VideoController(
					videoPath = resolvedPath,
					totalDuration = totalDuration,
					currentPosition = currentPlaybackPosition,
					onRangeChange = { min, max ->
						lastMinValue = min
						lastMaxValue = max
						if (player.currentPosition / 1000 !in min .. max) {
							player.seekTo(min * 1000)
							currentPlaybackPosition = min
						}
					},
					onSeekChange = { seek ->
						player.seekTo(seek * 1000)
						currentPlaybackPosition = seek
					}
				)
			}
			
			if (isProcessing) {
				Box(
					modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
					contentAlignment = Alignment.Center
				) {
					CircularProgressIndicator(color = Color.White)
				}
			}
		}
	}
}

@Composable
fun VideoController(
	videoPath: String?,
	totalDuration: Long,
	currentPosition: Long,
	onRangeChange: (Long, Long) -> Unit,
	onSeekChange: (Long) -> Unit
) {
	var lastInitializedDuration by remember { mutableLongStateOf(- 1L) }
	var lastLoadedThumbnailsPath by remember { mutableStateOf<String?>(null) }
	
	Box(
		modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 20.dp)
	) {
		AndroidView(
			factory = { ctx ->
				val view = LayoutInflater.from(ctx).inflate(R.layout.view_video_controller, null)
				val rangeSeekBar = view.findViewById<CrystalRangeSeekbar>(R.id.range_seek_bar)
				val seekBarController = view.findViewById<CrystalSeekbar>(R.id.seekbar_controller)
				
				rangeSeekBar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
					onRangeChange(minValue.toLong(), maxValue.toLong())
				}
				
				seekBarController.setOnSeekbarFinalValueListener { value ->
					onSeekChange(value.toLong())
				}
				
				view
			},
			update = { view ->
				val rangeSeekBar = view.findViewById<CrystalRangeSeekbar>(R.id.range_seek_bar)
				val seekBarController = view.findViewById<CrystalSeekbar>(R.id.seekbar_controller)
				val imageViews = arrayOf<ImageView>(
					view.findViewById(R.id.image_one),
					view.findViewById(R.id.image_two),
					view.findViewById(R.id.image_three),
					view.findViewById(R.id.image_four),
					view.findViewById(R.id.image_five),
					view.findViewById(R.id.image_six),
					view.findViewById(R.id.image_seven),
					view.findViewById(R.id.image_eight)
				)
				
				if (totalDuration > 0) {
					// Update seeker position during playback
					seekBarController.setMaxValue(totalDuration.toFloat()).apply()
					seekBarController.setMinStartValue(currentPosition.toFloat()).apply()
					
					// Initialize range seekbar if duration has changed
					if (lastInitializedDuration != totalDuration) {
						rangeSeekBar.setMaxValue(totalDuration.toFloat())
							.setMinStartValue(0f)
							.setMaxStartValue(totalDuration.toFloat())
							.apply()
						lastInitializedDuration = totalDuration
					}
					
					// Load thumbnails if not already loaded for this path
					if (videoPath != null && lastLoadedThumbnailsPath != videoPath) {
						val diff = totalDuration.toFloat() / 8
						val file = File(videoPath)
						for (i in 0 until 8) {
							val interval = ((i + 1) * diff * 1000000).toLong()
							val options = RequestOptions().frame(interval)
							Glide.with(view.context)
								.load(file)
								.apply(options)
								.transition(DrawableTransitionOptions.withCrossFade(300))
								.into(imageViews[i])
						}
						lastLoadedThumbnailsPath = videoPath
					}
				}
			},
			modifier = Modifier.fillMaxWidth()
		)
	}
}

private fun trimVideo(
	context: Activity,
	filePath: String,
	lastMinValue: Long,
	lastMaxValue: Long,
	onFinish: (String) -> Unit,
	onError: () -> Unit
) {
	val outputPath = getFileName(context, Uri.parse(filePath))
	val command = arrayOf(
		"-ss", TrimmerUtils.formatCSeconds(lastMinValue),
		"-i", filePath,
		"-t", TrimmerUtils.formatCSeconds(lastMaxValue - lastMinValue),
		"-async", "1", "-strict", "-2", "-c", "copy", outputPath
	)
	
	FFmpegKit.executeWithArgumentsAsync(command) { session ->
		val result = session.returnCode.value
		
		context.runOnUiThread {
			if (result == 0) {
				onFinish(outputPath)
			} else {
				// Retry with accurate command if copy fails
				val accurateCommand = arrayOf(
					"-ss", TrimmerUtils.formatCSeconds(lastMinValue),
					"-i", filePath,
					"-t", TrimmerUtils.formatCSeconds(lastMaxValue - lastMinValue),
					"-async", "1", "-vcodec", "mpeg4", "-qscale:v", "2",
					"-acodec", "aac", "-b:a", "128k", outputPath
				)
				FFmpegKit.executeWithArgumentsAsync(accurateCommand) { secondSession ->
					val secondResult = secondSession.returnCode.value
					
					context.runOnUiThread {
						if (secondResult == 0) {
							onFinish(outputPath)
						} else {
							onError()
						}
					}
				}
			}
		}
	}
}

private fun getFileName(context: Context, filePath: Uri): String {
	val path = context.getExternalFilesDir("TrimmedVideo")?.path
	val calendar = Calendar.getInstance()
	val fileDateTime =
			"${calendar.get(Calendar.YEAR)}_${calendar.get(Calendar.MONTH)}_${calendar.get(Calendar.DAY_OF_MONTH)}_${
				calendar.get(Calendar.HOUR_OF_DAY)
			}_${calendar.get(Calendar.MINUTE)}_${calendar.get(Calendar.SECOND)}"
	val fName = "trimmed_video_"
	val newFile = File(
		path + File.separator + fName + fileDateTime + "." + TrimmerUtils.getFileExtension(
			context,
			filePath
		)
	)
	return newFile.absolutePath
}
