package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun SaveButton(show: Boolean, onClick: () -> Unit) {
	AnimatedVisibility(
		show,
		enter = fadeIn(),
		exit = fadeOut()
	) {
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = PLACEHOLDER_16DP)
				.clickable { onClick() },
			text = stringResource(R.string.save),
			softWrap = true,
			color = colorResource(com.bellogatecaliphate.core.R.color.quickreels_purple),
			textAlign = TextAlign.Center,
		)
	}
}