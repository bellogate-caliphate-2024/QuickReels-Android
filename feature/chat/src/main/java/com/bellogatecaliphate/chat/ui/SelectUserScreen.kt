package com.bellogatecaliphate.chat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.chat.model.UiState
import com.bellogatecaliphate.chat.ui.users.UsersList
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.ui.ProgressBar

@Composable
fun SelectUserScreen(
	viewModel: SelectUserViewmodel = hiltViewModel(),
	onUserSelected: (chatUser: User) -> Unit
) {
	val uiState = viewModel.uiState.collectAsStateWithLifecycle()
	SelectUserScreen(uiState.value, onUserSelected)
}

@Composable
private fun SelectUserScreen(uiState: UiState, onUserSelected: (chatUser: User) -> Unit) {
	var searchText by remember { mutableStateOf("") }
	Column {
		ProgressBar(uiState.isLoading)
		BasicTextField(
			modifier = Modifier.fillMaxWidth(),
			value = searchText, onValueChange = { newValue -> searchText = newValue }
		)
		UsersList(uiState.listOfUsers.collectAsLazyPagingItems(), onUserSelected)
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewSelectUserScreen() {
	SelectUserScreen(UiState(isLoading = true)) {}
}