package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.bellogatecaliphate.core.R

@Composable
internal fun ReplyText(visible: Boolean, onReplyClicked: () -> Unit) {
	if (visible.not()) return
	Text(
		modifier = Modifier.clickable { onReplyClicked() },
		text = stringResource(id = R.string.reply),
		color = colorResource(id = R.color.ash)
	)
}