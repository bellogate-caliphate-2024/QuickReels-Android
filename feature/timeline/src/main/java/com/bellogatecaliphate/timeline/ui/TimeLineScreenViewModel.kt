package com.bellogatecaliphate.timeline.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bellogatecaliphate.domain.contents.GetContentsUseCase
import com.bellogatecaliphate.domain.contents.like.LikeContentUseCase
import com.bellogatecaliphate.timeline.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeLineScreenViewModel @Inject constructor(
	val getContentsUseCase: GetContentsUseCase,
	val likeContentUseCase: LikeContentUseCase,
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	internal val uiState = _uiState.asStateFlow()
	
	init {
		getContents()
	}
	
	private fun getContents() = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
		val response = getContentsUseCase().cachedIn(viewModelScope)
		_uiState.update { it.copy(listOfContents = response, isLoading = false) }
	}
	
	fun likeContent(contentId: String, isLiked: Boolean) = viewModelScope.launch {
		likeContentUseCase(contentId, isLiked)
	}
	
	fun getComments() {
	
	}
}