package com.bellogatecaliphate.chat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import kotlinx.coroutines.delay

@Composable
fun SelectUserScreen(
	viewModel: SelectUserViewmodel = hiltViewModel(),
	onUserSelected: (chatUser: User) -> Unit
) {
	val uiState = viewModel.uiState.collectAsStateWithLifecycle()
	SelectUserScreen(uiState.value, onUserSelected) { viewModel.searchForUser(it) }
}

@Composable
private fun SelectUserScreen(
	uiState: UiState,
	onUserSelected: (chatUser: User) -> Unit,
	onSearch: (String) -> Unit
) {
	val scope = rememberCoroutineScope()
	var searchText by remember { mutableStateOf("") }
	Column {
		ProgressBar(uiState.isLoading)
		OutlinedTextField(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_8DP),
			value = searchText,
			onValueChange = { searchText = it },
			placeholder = { Text("Search") }
		)
		UsersList(uiState.listOfUsers.collectAsLazyPagingItems(), onUserSelected)
	}
	LaunchedEffect(key1 = searchText) {
		if (searchText.isBlank()) return@LaunchedEffect
		delay(2000) // Debounce delay of 1 second
		onSearch(searchText)
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewSelectUserScreen() {
	SelectUserScreen(UiState(isLoading = true), {}, {})
}