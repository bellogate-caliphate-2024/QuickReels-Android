package com.bellogatecaliphate.account.ui.screens.profile_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.domain.user.GetUserEmailUseCase
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
	private val getUserEmailUseCase: GetUserEmailUseCase,
	private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	
	fun getUserInfo(userEmail: String) = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true, unableToGetUser = false) }
		val user = getUserInfoUseCase(userEmail)
		_uiState.update {
			it.copy(
				isLoading = false,
				user = user,
				unableToGetUser = user == null,
				accountBelongsToLoggedInUser = getUserEmailUseCase() == userEmail
			)
		}
	}
}