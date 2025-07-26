package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.bellogatecaliphate.core.R

@Composable
internal fun DeleteText(visible: Boolean, onDeleteClicked: () -> Unit) {
	if (visible.not()) return
	Text(
		modifier = Modifier.clickable { },
		text = stringResource(R.string.delete),
		color = colorResource(id = R.color.quickreels_delete_color),
		style = MaterialTheme.typography.bodySmall
	)
}