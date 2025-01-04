package com.bellogatecaliphate.account.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bellogatecaliphate.account.model.UiState
import com.bellogatecaliphate.domain.contents.GetContentsHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject constructor(
	private val getContentsHistoryUseCase: GetContentsHistoryUseCase,
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	init {
		getUserContentHistory()
	}
	
	private fun getUserContentHistory() {
		_uiState.update { it.copy(isLoading = true) }
		val listOfContentHistory =
				getContentsHistoryUseCase().cachedIn(viewModelScope)
		_uiState.update { it.copy(listOfContentHistory = listOfContentHistory, isLoading = false) }
	}
}