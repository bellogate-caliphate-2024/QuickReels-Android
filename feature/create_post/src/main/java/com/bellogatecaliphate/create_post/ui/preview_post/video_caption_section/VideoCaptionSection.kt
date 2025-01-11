package com.bellogatecaliphate.create_post.ui.preview_post.video_caption_section

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun VideoCaptionSection(
	isReadOnly: Boolean,
	descriptionText: String?,
	onValueChange: (String) -> Unit
) {
	OutlinedTextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(PLACEHOLDER_16DP),
		value = descriptionText ?: "",
		readOnly = isReadOnly,
		onValueChange = onValueChange,
		label = { Text("Add a caption...") })
}