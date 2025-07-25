package com.bellogatecaliphate.create_post.ui.preview_post.sections.bottom_section

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
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
			QuickReelsCircularProgressBar()
			Spacer(Modifier.height(PLACEHOLDER_8DP))
		} else {
			SendButton { onSendButtonClicked(text) }
		}
	}
}