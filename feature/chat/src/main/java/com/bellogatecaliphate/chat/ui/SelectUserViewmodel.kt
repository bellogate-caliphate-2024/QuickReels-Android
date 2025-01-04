package com.bellogatecaliphate.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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
	private val getChatUsersUseCase: GetChatUsersUseCase,
	private val searchForChatUsersUseCase: SearchForChatUsersUseCase
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	init {
		getUsers()
	}
	
	private fun getUsers() = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
		val response = getChatUsersUseCase().cachedIn(viewModelScope)
		_uiState.update { it.copy(listOfUsers = response, isLoading = false) }
	}
	
	fun searchForUser(userName: String) = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
	}
}