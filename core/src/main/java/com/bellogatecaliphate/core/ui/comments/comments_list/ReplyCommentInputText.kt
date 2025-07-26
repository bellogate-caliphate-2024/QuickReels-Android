package com.bellogatecaliphate.core.ui.comments.comments_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun ReplyCommentInputText(
	visible: Boolean = false,
	onReply: (String) -> Unit = {},
	onClose: () -> Unit = {}
) {
	if (visible.not()) return
	val context = LocalContext.current
	var text by remember { mutableStateOf("") }
	
	Column(Modifier.fillMaxWidth()) {
		TextField(
			modifier = Modifier
				.fillMaxWidth(),
			value = text,
			onValueChange = { text = it },
			shape = RoundedCornerShape(8.dp),
			colors = TextFieldDefaults.colors(
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent
			)
		)
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Row {
			SendButton {
				if (text.isBlank())
					Toast.makeText(
						context,
						context.getString(R.string.please_enter_a_reply), Toast.LENGTH_SHORT
					).show()
				else onReply(text)
			}
			Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
			Text(
				stringResource(id = R.string.close), Modifier.clickable { onClose() },
				color = colorResource(id = R.color.purple_300),
				style = MaterialTheme.typography.bodySmall
			)
		}
	}
}

@Composable
private fun SendButton(
	onSendPressed: () -> Unit = {},
) {
	Text(
		stringResource(id = R.string.send), Modifier.clickable { onSendPressed() },
		color = colorResource(id = R.color.purple_300),
		style = MaterialTheme.typography.bodySmall
	)
}

@Preview(showBackground = true)
@Composable
private fun PreviewReplyCommentInputText() {
	ReplyCommentInputText(true)
}
