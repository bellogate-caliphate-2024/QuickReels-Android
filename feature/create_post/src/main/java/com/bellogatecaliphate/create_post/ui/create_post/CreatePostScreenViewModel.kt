package com.bellogatecaliphate.create_post.ui.create_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.create_post.model.UiState
import com.bellogatecaliphate.domain.post.GetOngoingPostsUploadStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostScreenViewModel @Inject constructor(
	private val getOngoingPostsUploadStatusUseCase: GetOngoingPostsUploadStatusUseCase
) : ViewModel() {
	
	private val _state = MutableStateFlow(UiState())
	val state = _state.asStateFlow()
	
	init {
		observeOngoingPostUploads()
	}
	
	private fun observeOngoingPostUploads() = viewModelScope.launch {
		getOngoingPostsUploadStatusUseCase().collect { liveResults ->
			_state.update { it.copy(existingUploads = liveResults) }
		}
	}
	
	fun requestPermissionAndOpenGallery() {
		_state.update { it.copy(requestStoragePermissionAndOpenGallery = true) }
	}
	
	fun resetGalleryState() {
		_state.update { it.copy(requestStoragePermissionAndOpenGallery = false) }
	}
}