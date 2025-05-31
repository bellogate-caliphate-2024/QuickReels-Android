package com.bellogatecaliphate.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import com.bellogatecaliphate.core.R
import com.spr.jetpack_loading.components.indicators.BallScaleRippleIndicator

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
fun QuickReelsProgressBar(show: Boolean) {
	if (show.not()) return
	BallScaleRippleIndicator(
		color = colorResource(id = R.color.light_purple),
	)
}