package com.bellogatecaliphate.account.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.account.model.UiState
import com.bellogatecaliphate.account.ui.screens.AnonymousUserAccountScreen
import com.bellogatecaliphate.account.ui.screens.LoggedInUserAccountScreen
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun AccountScreen(
	serverClientId: String,
	viewModel: AccountScreenViewModel = hiltViewModel(),
) {
	val state = viewModel.uiState.collectAsStateWithLifecycle().value
	val context = LocalContext.current
	AccountScreen(uiState = state, onLogin = {
		viewModel.performLogin {
			viewModel.firebaseAuthentication.performLogin(context, serverClientId)
		}
	})
}

@Composable
private fun AccountScreen(uiState: UiState, onLogin: () -> Unit = {}) {
	Box(
		modifier = Modifier
			.background(Color.White)
			.fillMaxSize()
			.padding(PLACEHOLDER_16DP),
	) {
		if (uiState.isUserLoggedIn) {
			LoggedInUserAccountScreen(
				uiState = uiState,
				listOfContentHistory = uiState.listOfContentHistory.collectAsLazyPagingItems()
			)
		} else {
			AnonymousUserAccountScreen(uiState, onLogin)
		}
	}
}

@Preview
@Composable
private fun AccountScreenPreview() {
	AccountScreen(UiState())
}