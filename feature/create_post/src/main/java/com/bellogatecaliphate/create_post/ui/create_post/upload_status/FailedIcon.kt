package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
internal fun FailedIcon(size: Dp) {
	val modifier = Modifier.size(size)
	Column(modifier.background(Color.Transparent)) {
		val composition by rememberLottieComposition(
			spec = LottieCompositionSpec.Url("https://lottie.host/dee967ed-ac43-476d-b338-398332de1edb/98HkUV6ghq.lottie")
		)
		val progress by animateLottieCompositionAsState(
			composition = composition,
			iterations = LottieConstants.IterateForever,
		)
		LottieAnimation(
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