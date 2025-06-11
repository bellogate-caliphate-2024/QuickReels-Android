package com.bellogatecaliphate.account.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun LoginButton(visible: Boolean, onLoginClicked: () -> Unit) {
	if (visible.not()) return
	OutlinedButton(
		onClick = onLoginClicked,
		border = BorderStroke(
			width = 1.dp,
			color = colorResource(com.bellogatecaliphate.core.R.color.quickreels_purple)
		)
	) {
		Row(verticalAlignment = Alignment.CenterVertically) {
			Image(
				modifier = Modifier.size(24.dp),
				painter = painterResource(com.bellogatecaliphate.core.R.drawable.google),
				contentDescription = ""
			)
			Spacer(Modifier.width(PLACEHOLDER_8DP))
			Text(
				color = colorResource(com.bellogatecaliphate.core.R.color.quickreels_purple),
				text = stringResource(id = R.string.continue_with_google)
			)
		}
	}
}