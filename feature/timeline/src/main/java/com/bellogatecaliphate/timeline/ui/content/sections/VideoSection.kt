package com.bellogatecaliphate.timeline.ui.content.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.timeline.R

@Composable
internal fun VideoSection(modifier: Modifier, videoUrl: String) {
	Column(modifier.background(colorResource(id = com.bellogatecaliphate.core.R.color.purple_500))) {
		Image(
			painter = painterResource(id = R.drawable.placeholder_image),
			contentDescription = "content description",
			modifier = Modifier.fillMaxSize()
		)
	}
}