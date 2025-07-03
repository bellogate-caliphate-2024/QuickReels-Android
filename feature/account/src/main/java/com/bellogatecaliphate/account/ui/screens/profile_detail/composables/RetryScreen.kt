package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.bellogatecaliphate.account.R

@Composable
internal fun RetryScreen(unableToGetUserInfo: Boolean, onRetry: () -> Unit) {
	if (unableToGetUserInfo.not()) return
	Column(
		modifier = Modifier
			.background(color = Color.White)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			stringResource(R.string.unable_to_get_user_info),
			modifier = Modifier.clickable(onClick = onRetry),
		)
	}
}