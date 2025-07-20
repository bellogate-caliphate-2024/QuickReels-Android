package com.bellogatecaliphate.account.ui.screens.view_and_edit_content.captions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.domain.contents.EditContentCaptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EditCaptionViewModel @Inject constructor(
	private val editCaptionUseCase: EditContentCaptionUseCase
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	
	fun editCaption(contentId: String, newCaption: String) = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true, savedSuccessfully = null) }
		val result = editCaptionUseCase(contentId, newCaption)
		_uiState.update {
			it.copy(isLoading = false, savedSuccessfully = result)
		}
	}
	
	fun cancelOngoingWork() {
		_uiState.value = UiState()
		viewModelScope.coroutineContext.cancelChildren() // Cancels all coroutines launched in this scope
	}
}