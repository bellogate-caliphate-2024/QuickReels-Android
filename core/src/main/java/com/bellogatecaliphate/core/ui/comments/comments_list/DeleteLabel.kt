package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.bellogatecaliphate.core.R

@Composable
internal fun DeleteLabel(visible: Boolean, onDeleteConfirmationGiven: () -> Unit) {
	if (visible.not()) return
	
	var openDeleteConfirmationPrompt by remember { mutableStateOf(false) }
	Text(
		modifier = Modifier.clickable { openDeleteConfirmationPrompt = true },
		text = stringResource(R.string.delete),
		color = colorResource(id = R.color.quickreels_delete_color),
		style = MaterialTheme.typography.bodySmall
	)
	
	if (openDeleteConfirmationPrompt) {
		AlertDialog(
			onDismissRequest = {
				openDeleteConfirmationPrompt = false
			},
			title = { Text(stringResource(R.string.confirm)) },
			text = { Text(stringResource(R.string.are_you_sure_you_want_to_delete_this_comment)) },
			confirmButton = {
				TextButton(onClick = {
					openDeleteConfirmationPrompt = false
					onDeleteConfirmationGiven()
				}) { Text(stringResource(R.string.yes)) }
			},
			dismissButton = {
				TextButton(onClick = {
					openDeleteConfirmationPrompt = false
				}) { Text(stringResource(R.string.no)) }
			}
		)
	}
}