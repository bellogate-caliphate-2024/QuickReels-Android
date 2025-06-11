package com.bellogatecaliphate.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ProgressBar(show: Boolean) {
	if (show.not()) return
	LinearProgressIndicator(
		modifier = Modifier
			.fillMaxWidth()
			.testTag("progressBar")
	)
}

@Composable
fun QuickReelsCircularProgressBar(show: Boolean = true, size: Dp = 40.dp) {
	if (show.not()) return
	val modifier = Modifier.size(size)
	Column(modifier.background(Color.Transparent)) {
		val composition by rememberLottieComposition(
			spec = LottieCompositionSpec.Url("https://lottie.host/ad57e9a5-e41a-4aa6-ae2a-93eabb438509/prqBpiIgeF.lottie")
		)
		val progress by animateLottieCompositionAsState(
			composition = composition,
			iterations = LottieConstants.IterateForever,
		)
		LottieAnimation(
			contentScale = ContentScale.FillBounds,
			modifier = modifier,
			maintainOriginalImageBounds = true,
			renderMode = RenderMode.AUTOMATIC,
			composition = composition,
			progress = {
				progress
			}
		)
	}
}