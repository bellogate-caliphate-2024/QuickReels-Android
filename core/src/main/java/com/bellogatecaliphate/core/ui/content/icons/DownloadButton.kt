package com.bellogatecaliphate.core.ui.content.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.R

@Composable
fun DualColorButton(
	onClickDownload: () -> Unit
) {
	Row(
		modifier = Modifier
			.clickable { onClickDownload() }
			.clip(RoundedCornerShape(24.dp))
			.background(Color.Transparent)
	) {
		Box(
			modifier = Modifier
				.background(colorResource(R.color.quickreels_yellow))
				.padding(horizontal = 12.dp, vertical = 4.dp),
			contentAlignment = Alignment.Center
		) {
			Text(
				text = "stream",
				color = Color.White,
				fontWeight = FontWeight.Bold,
				style = MaterialTheme.typography.bodySmall
			)
		}
		
		Box(
			modifier = Modifier
				.background(colorResource(R.color.quickreels_purple))
				.padding(horizontal = 12.dp, vertical = 4.dp),
			contentAlignment = Alignment.Center
		) {
			Text(
				text = "download",
				color = Color.White,
				fontWeight = FontWeight.Bold,
				style = MaterialTheme.typography.bodySmall
			)
		}
	}
}