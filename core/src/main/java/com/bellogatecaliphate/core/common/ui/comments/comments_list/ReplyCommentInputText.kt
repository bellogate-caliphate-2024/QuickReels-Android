package com.bellogatecaliphate.core.common.ui.comments.comments_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bellogatecaliphate.core.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.R

@Composable
internal fun ReplyCommentInputText(
	visible: Boolean = false,
	onReply: (String) -> Unit = {},
	onClose: () -> Unit = {}
) {
	if (visible.not()) return
	var text by remember { mutableStateOf("") }
	
	Column(Modifier.fillMaxWidth()) {
		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = text,
			onValueChange = { text = it }
		)
		Row {
			Text(
				stringResource(id = R.string.send), Modifier.clickable { onReply(text) },
				color = colorResource(id = R.color.purple_300)
			)
			Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
			Text(
				stringResource(id = R.string.close), Modifier.clickable { onClose() },
				color = colorResource(id = R.color.purple_300)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewReplyCommentInputText() {
	ReplyCommentInputText(true)
}
