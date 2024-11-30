package com.bellogatecaliphate.contents_history.ui

import com.bellogatecaliphate.contents_history.model.UiState
import com.bellogatecaliphate.domain.contents.GetContentsHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ContentHistoryViewModel @Inject constructor(private val getContentsHistoryUseCase: GetContentsHistoryUseCase) {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	sssss
	fun getContentHistory(userEmail: String) =
			getContentsHistoryUseCase(userEmail).flow.map { pagingData -> pagingData }
}