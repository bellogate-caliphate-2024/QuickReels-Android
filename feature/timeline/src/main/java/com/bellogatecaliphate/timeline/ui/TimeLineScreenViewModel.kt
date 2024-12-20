package com.bellogatecaliphate.timeline.ui

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.domain.contents.GetContentsUseCase
import com.bellogatecaliphate.timeline.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TimeLineScreenViewModel @Inject constructor(
	val getContentsUseCase: GetContentsUseCase
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	fun getContents(): Flow<PagingData<Content>> {
		_uiState.update { it.copy(loading = true) }
		return getContentsUseCase()
	}
	
	fun setContents(contents: LazyPagingItems<Content>) {
		_uiState.update { it.copy(listOfContents = contents) }
	}
	
}