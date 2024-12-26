package com.bellogatecaliphate.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.chat.model.UiState
import com.bellogatecaliphate.domain.chat.GetChatUsersUseCase
import com.bellogatecaliphate.domain.chat.SearchForChatUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectUserViewmodel @Inject constructor(
	val getChatUsersUseCase: GetChatUsersUseCase,
	val searchForChatUsersUseCase: SearchForChatUsersUseCase
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	fun getUsers() = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
	}
	
	fun searchForUser(userName: String) = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
	}
}