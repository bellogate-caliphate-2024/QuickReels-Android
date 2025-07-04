package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_TEXT_SIZE_20

@Composable
internal fun FollowAndUnfollowButtonsSection(
	show: Boolean,
	isUpdatingFollowingStatus: Boolean,
	isFollowing: Boolean,
	onFollowClicked: () -> Unit,
	onUnfollowClicked: () -> Unit
) {
	QuickReelsCircularProgressBar(show = isUpdatingFollowingStatus)
	if (show.not() || isUpdatingFollowingStatus) return
	if (isFollowing) {
		OutlinedButton(
			onClick = onUnfollowClicked,
			border = BorderStroke(
				width = 1.dp,
				color = colorResource(com.bellogatecaliphate.core.R.color.quickreels_purple)
			)
		) {
			Text(
				color = colorResource(com.bellogatecaliphate.core.R.color.quickreels_purple),
				text = stringResource(id = R.string.unfollow)
			)
		}
	} else {
		Button(
			colors = ButtonDefaults.filledTonalButtonColors(
				containerColor = colorResource(com.bellogatecaliphate.core.R.color.quickreels_purple),
				contentColor = Color.White
			),
			onClick = onFollowClicked
		) {
			Text(stringResource(R.string.follow), fontSize = PLACEHOLDER_TEXT_SIZE_20)
		}
	}
}