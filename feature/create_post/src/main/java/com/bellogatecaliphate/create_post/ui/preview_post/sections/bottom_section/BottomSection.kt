package com.bellogatecaliphate.create_post.ui.preview_post.sections.bottom_section

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun BottomSection(
	text: String,
	isReadOnly: Boolean,
	isLoading: Boolean,
	onSendButtonClicked: (videoCaption: String) -> Unit
) {
	if (isReadOnly.not()) {
		if (isLoading) {
			CircularProgressIndicator(modifier = Modifier.padding(PLACEHOLDER_8DP))
		} else {
			SendButton { onSendButtonClicked(text) }
		}
	}
}