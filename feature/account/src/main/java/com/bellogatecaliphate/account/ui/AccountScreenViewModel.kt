package com.bellogatecaliphate.account.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bellogatecaliphate.account.model.UiState
import com.bellogatecaliphate.account.ui.authentication.FirebaseAuthentication
import com.bellogatecaliphate.domain.contents.GetContentsHistoryUseCase
import com.bellogatecaliphate.domain.user.CheckUserLoginUseCase
import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
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
	private val getLoggedInUserEmailUseCase: GetLoggedInUserEmailUseCase,
	val firebaseAuthentication: FirebaseAuthentication
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	fun findUser(userEmail: String?) {
		if (userEmail == null) {
			checkUserLogin()
		} else {
			getUserDetailsAndContentHistory(userEmail)
		}
	}
	
	fun performLogin(onOpenGoogleAuthenticationLoginScreen: suspend () -> Boolean) =
			viewModelScope.launch {
				_uiState.update { it.copy(isLoading = true) }
				val isLoginSuccessful = onOpenGoogleAuthenticationLoginScreen()
				_uiState.update { it.copy(isLoading = isLoginSuccessful) }
				checkUserLogin()
			}
	
	private fun checkUserLogin() = viewModelScope.launch {
		val isUserLoggedIn = checkUserLoginUseCase()
		_uiState.update { it.copy(isUserLoggedIn = isUserLoggedIn) }
		if (isUserLoggedIn) {
			getLoggedInUserEmailUseCase()?.let { getUserDetailsAndContentHistory(it) }
		}
	}
	
	private fun getUserDetailsAndContentHistory(userEmail: String) {
		getUserDetails(userEmail)
		getUserContentHistory(userEmail)
	}
	
	private fun getUserDetails(userEmail: String) = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
		val user = getUserInfoUseCase(userEmail)
		_uiState.update {
			it.copy(
				user = user,
				isLoading = false,
				networkError = user == null,
				isUserLoggedIn = user != null
			)
		}
	}
	
	private fun getUserContentHistory(userEmail: String) {
		val listOfContentHistory = getContentsHistoryUseCase(userEmail).cachedIn(viewModelScope)
		_uiState.update { it.copy(listOfContentHistory = listOfContentHistory) }
	}
}