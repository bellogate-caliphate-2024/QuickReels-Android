package com.bellogatecaliphate.chat.ui.cancel_search_icon

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.account.R

@Composable
internal fun CancelSearchIcon(visible: Boolean, onCancelClicked: () -> Unit) {
	if (visible.not()) return
	IconButton(onClick = onCancelClicked) {
		Icon(
			painter = painterResource(id = R.drawable.close),
			contentDescription = "Clear"
		)
	}
}