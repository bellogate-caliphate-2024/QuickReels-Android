package com.bellogatecaliphate.account.ui

import androidx.lifecycle.ViewModel
import com.bellogatecaliphate.account.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject constructor() : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
}