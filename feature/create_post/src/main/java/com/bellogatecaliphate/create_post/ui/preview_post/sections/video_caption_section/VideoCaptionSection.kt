package com.bellogatecaliphate.create_post.ui.preview_post.sections.video_caption_section

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
		OutlinedTextField(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_16DP),
			value = descriptionText ?: "",
			onValueChange = onValueChange,
			label = { Text("Add a caption...") })
	}
}