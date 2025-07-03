package com.bellogatecaliphate.account.ui.screens.profile_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.domain.user.CheckFollowingUseCase
import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
	private val getLoggedInUserEmailUseCase: GetLoggedInUserEmailUseCase,
	private val getUserInfoUseCase: GetUserInfoUseCase,
	private val checkFollowingUseCase: CheckFollowingUseCase
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	
	fun getUserInfo(userEmail: String) = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true, unableToGetUser = false) }
		val user = getUserInfoUseCase(userEmail)
		val userIsAFollower = checkFollowingUseCase(
			loggedInUserEmail = getLoggedInUserEmailUseCase() ?: "",
			emailOfUserToCheckFollowingStatus = userEmail
		)
		_uiState.update {
			it.copy(
				isLoading = false,
				user = user,
				unableToGetUser = user == null,
				isFollowing = userIsAFollower,
				accountBelongsToLoggedInUser = getLoggedInUserEmailUseCase() == userEmail
			)
		}
	}
}