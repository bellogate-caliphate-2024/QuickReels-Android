package com.bellogatecaliphate.create_post.ui.create_post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.util.PLACEHOLDER_TEXT_SIZE_20
import com.bellogatecaliphate.create_post.R

@Composable
internal fun SelectVideoButton(openGallery: () -> Unit) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.background(color = Color.White)
			.padding(10.dp)
	) {
		TextButton(onClick = openGallery, modifier = Modifier.align(Alignment.BottomCenter)) {
			Text(stringResource(R.string.select_video), fontSize = PLACEHOLDER_TEXT_SIZE_20)
		}
	}
}