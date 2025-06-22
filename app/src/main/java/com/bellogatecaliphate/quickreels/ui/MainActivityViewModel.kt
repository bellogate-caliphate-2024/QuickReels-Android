package com.bellogatecaliphate.quickreels.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainActivityViewModel @Inject constructor(
	val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	
	init {
		setCurrentUser()
	}
	
	fun onProfilePictureChanged(profilePictureUrl: String?) = viewModelScope.launch {
		val currentUser = getUserInfoUseCase()
		_uiState.update {
			it.copy(user = currentUser?.copy(profilePictureUrl = profilePictureUrl ?: ""))
		}
	}
	
	private fun setCurrentUser() = viewModelScope.launch {
		getUserInfoUseCase()?.let { loggedInUser ->
			_uiState.update { it.copy(user = loggedInUser) }
		}
	}
}