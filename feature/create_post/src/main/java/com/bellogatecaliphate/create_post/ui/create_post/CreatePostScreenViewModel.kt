package com.bellogatecaliphate.create_post.ui.create_post

import androidx.lifecycle.ViewModel
import com.bellogatecaliphate.create_post.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreatePostScreenViewModel @Inject constructor() : ViewModel() {
	
	private val _state = MutableStateFlow(UiState())
	val state = _state.asStateFlow()
	
	fun requestPermissionAndOpenGallery() {
		_state.update { it.copy(requestStoragePermissionAndOpenGallery = true) }
	}
}