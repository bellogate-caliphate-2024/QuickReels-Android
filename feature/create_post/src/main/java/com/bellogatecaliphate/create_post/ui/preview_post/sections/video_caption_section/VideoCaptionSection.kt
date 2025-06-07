package com.bellogatecaliphate.create_post.ui.preview_post.sections.video_caption_section

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun VideoCaptionSection(
	isReadOnly: Boolean,
	descriptionText: String?,
	onValueChange: (String) -> Unit
) {
	if (isReadOnly) {
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_16DP),
			text = descriptionText ?: "",
			softWrap = true,
			color = Color.Gray
		)
	} else {
		TextField(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_16DP),
			value = descriptionText ?: "",
			onValueChange = onValueChange,
			label = { Text("Add a caption...") },
			shape = RoundedCornerShape(8.dp),
			colors = TextFieldDefaults.colors(
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent
			)
		)
	}
}