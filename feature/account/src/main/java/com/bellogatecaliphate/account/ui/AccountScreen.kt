package com.bellogatecaliphate.account.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.account.model.UiState

@Composable
fun AccountScreen(viewModel: AccountScreenViewModel = hiltViewModel()) {
	val state = viewModel.uiState.collectAsStateWithLifecycle().value
	AccountScreen(state)
}

@Composable
fun AccountScreen(uiState: UiState) {

}