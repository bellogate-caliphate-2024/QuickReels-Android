package com.bellogatecaliphate.account.ui.screens.view_and_edit_content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.domain.contents.GetContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewAndEditContentScreenViewModel @Inject constructor(
	private val getContentUseCase: GetContentUseCase,
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	
	fun getContent(isRefreshing: Boolean = false, contentId: String) = viewModelScope.launch {
		_uiState.value = _uiState.value.copy(
			isRefreshing = isRefreshing,
			isLoadingInitialContent = isRefreshing.not(),
		)
		val result = getContentUseCase(contentId)
		
		_uiState.value = _uiState.value.copy(
			isRefreshing = false,
			isLoadingInitialContent = false,
			content = result,
			errorLoadingContent = result == null,
		)
	}
}