package com.bellogatecaliphate.account.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.model.UiState
import com.bellogatecaliphate.account.ui.composables.LoginButton
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun AnonymousUserAccountScreen(uiState: UiState, onLogin: () -> Unit) {
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			fontSize = 40.sp,
			fontWeight = FontWeight.Bold,
			color = colorResource(id = com.bellogatecaliphate.core.R.color.quickreels_purple),
			text = stringResource(id = R.string.join)
		)
		Spacer(Modifier.height(PLACEHOLDER_8DP))
		Text(text = stringResource(id = R.string.create_account), textAlign = TextAlign.Center)
		Spacer(Modifier.height(PLACEHOLDER_8DP))
		QuickReelsCircularProgressBar(uiState.isLoading)
		LoginButton(visible = uiState.isLoading.not(), onLoginClicked = onLogin)
	}
}