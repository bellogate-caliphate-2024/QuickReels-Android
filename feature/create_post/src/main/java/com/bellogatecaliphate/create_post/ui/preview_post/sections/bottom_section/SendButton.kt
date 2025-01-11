package com.bellogatecaliphate.create_post.ui.preview_post.sections.bottom_section

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
internal fun SendButton(onClick: () -> Unit) {
	TextButton(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
		Text("Send", fontSize = 20.sp)
	}
}