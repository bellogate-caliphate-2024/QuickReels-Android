package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
internal fun SuccessIcon(size: Dp) {
	val modifier = Modifier.size(size)
	Column(modifier.background(Color.Transparent)) {
		val composition by rememberLottieComposition(
			spec = LottieCompositionSpec.Url("https://lottie.host/574fef16-9a71-4a54-88e7-b7b10a04d851/bw9NNWQSvo.lottie")
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