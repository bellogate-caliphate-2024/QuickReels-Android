package com.bellogatecaliphate.timeline.ui.content.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bellogatecaliphate.core.ui.ProgressBar
import com.bellogatecaliphate.timeline.R

@Composable
internal fun VideoSection(modifier: Modifier, videoUrl: String, videoThumbnailUrl: String) {
	Column(modifier.background(Color.Black)) {
		ProgressBar(true)
		AsyncImage(
			model = videoThumbnailUrl,
			contentDescription = null,
			modifier = Modifier.fillMaxSize().blur(radius = 10.dp)
		)
	}
}