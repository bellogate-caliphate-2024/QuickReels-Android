package com.bellogatecaliphate.account.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bellogatecaliphate.account.model.UiState
import com.bellogatecaliphate.account.ui.authentication.FirebaseAuthentication
import com.bellogatecaliphate.domain.contents.GetContentsHistoryUseCase
import com.bellogatecaliphate.domain.user.CheckUserLoginUseCase
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject constructor(
	private val getContentsHistoryUseCase: GetContentsHistoryUseCase,
	private val checkUserLoginUseCase: CheckUserLoginUseCase,
	private val getUserInfoUseCase: GetUserInfoUseCase,
	val firebaseAuthentication: FirebaseAuthentication
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	init {
		checkUserLogin()
	}
	
	private fun checkUserLogin() = viewModelScope.launch {
		val isUserLoggedIn = checkUserLoginUseCase()
		_uiState.update { it.copy(isUserLoggedIn = isUserLoggedIn) }
		if (isUserLoggedIn) {
			setUpLoggedInUser()
		}
	}
	
	private fun setUpLoggedInUser() {
		getUserDetails()
		getUserContentHistory()
	}
	
	private fun getUserDetails() = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
		val user = getUserInfoUseCase()
		_uiState.update { it.copy(user = user, isLoading = false) }
	}
	
	private fun getUserContentHistory() {
		val listOfContentHistory =
				getContentsHistoryUseCase().cachedIn(viewModelScope)
		_uiState.update { it.copy(listOfContentHistory = listOfContentHistory, isLoading = false) }
	}
}