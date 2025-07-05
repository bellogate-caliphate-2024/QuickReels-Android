package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun LogOutButton(showLogoutLoading: Boolean, onOpenLogOutBottomSheet: () -> Unit) {
	if (showLogoutLoading.not()) {
		Text(
			fontWeight = FontWeight.Bold,
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = PLACEHOLDER_16DP)
				.clickable {
					onOpenLogOutBottomSheet()
				},
			text = stringResource(R.string.logout),
			softWrap = true,
			color = Color.Red,
			textAlign = TextAlign.Center,
		)
	}
	QuickReelsCircularProgressBar(showLogoutLoading)
}