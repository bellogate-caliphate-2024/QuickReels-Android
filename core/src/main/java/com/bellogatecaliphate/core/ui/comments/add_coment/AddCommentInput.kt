package com.bellogatecaliphate.core.ui.comments.add_coment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun AddCommentInput(
	isLoading: Boolean,
	commentUploadedSuccessfully: Boolean?,
	onSendButonClicked: (comment: String) -> Unit,
) {
	var comment by remember { mutableStateOf("") }
	val showSendButton = comment.isNotBlank() && isLoading.not()
	
	Column {
		TextField(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_16DP),
			value = comment,
			onValueChange = { comment = it },
			placeholder = { Text(stringResource(R.string.add_a_comment)) },
			shape = RoundedCornerShape(8.dp),
			colors = TextFieldDefaults.colors(
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent
			),
			trailingIcon = {
				TrailingIcon(isLoading = isLoading, showSendButton = showSendButton) {
					onSendButonClicked(comment)
				}
			}
		)
		
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
	}
	
	LaunchedEffect(commentUploadedSuccessfully) {
		if (commentUploadedSuccessfully == true) comment = ""
	}
}

@Composable
private fun TrailingIcon(
	isLoading: Boolean,
	showSendButton: Boolean,
	onSendButonClicked: () -> Unit
) {
	val size = 48.dp
	Box(Modifier.padding(PLACEHOLDER_8DP)) {
		QuickReelsCircularProgressBar(show = isLoading)
		if (showSendButton)
			Image(
				modifier = Modifier
					.height(size)
					.clickable { onSendButonClicked() },
				painter = painterResource(R.drawable.outline_arrow_upward_24),
				contentDescription = ""
			)
	}
}