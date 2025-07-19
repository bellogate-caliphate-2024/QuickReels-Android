package com.bellogatecaliphate.account.ui.screens.view_and_edit_content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.domain.contents.DeleteContentUseCase
import com.bellogatecaliphate.domain.contents.GetContentUseCase
import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ViewAndEditContentScreenViewModel @Inject constructor(
	private val getLoggedInUserEmailUseCase: GetLoggedInUserEmailUseCase,
	private val getContentUseCase: GetContentUseCase,
	private val deleteContentUseCase: DeleteContentUseCase,
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	
	fun getContent(isRefreshing: Boolean = false, contentId: String) = viewModelScope.launch {
		_uiState.update {
			it.copy(
				isRefreshing = isRefreshing,
				isLoadingInitialContent = isRefreshing.not(),
			)
		}
		val content = getContentUseCase(contentId)
		
		_uiState.update {
			it.copy(
				isRefreshing = false,
				isLoadingInitialContent = false,
				content = content,
				errorLoadingContent = content == null,
				contentBelongsToLoggedInUser = getLoggedInUserEmailUseCase() == content?.userId
			)
		}
	}
	
	fun deleteContent(contentId: String) = viewModelScope.launch {
		val result = deleteContentUseCase(contentId)
		_uiState.update {
			it.copy(deleteContentSuccess = result)
		}
	}
}