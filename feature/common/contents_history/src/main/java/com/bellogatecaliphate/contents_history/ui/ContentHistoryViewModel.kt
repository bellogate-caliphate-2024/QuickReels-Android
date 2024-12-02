package com.bellogatecaliphate.contents_history.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bellogatecaliphate.contents_history.model.UiState
import com.bellogatecaliphate.domain.contents.GetContentsHistoryUseCase
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentHistoryViewModel @Inject constructor(
	private val getContentsHistoryUseCase: GetContentsHistoryUseCase,
	private val userInfoUseCase: GetUserInfoUseCase
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	
	init {
		setUserEmail()
		getUserContentHistory()
	}
	
	private fun setUserEmail() = viewModelScope.launch {
		val userEmail = userInfoUseCase()?.email ?: ""
		_uiState.update { it.copy(userEmail = userEmail) }
	}
	
	private fun getUserContentHistory() {
		val listOfContentHistory =
				getContentsHistoryUseCase(uiState.value.userEmail).cachedIn(viewModelScope)
		_uiState.update { it.copy(content = listOfContentHistory) }
	}
}