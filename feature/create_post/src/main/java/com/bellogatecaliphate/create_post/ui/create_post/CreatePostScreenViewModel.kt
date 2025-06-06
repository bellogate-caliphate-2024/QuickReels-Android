package com.bellogatecaliphate.create_post.ui.create_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.model.UiState
import com.bellogatecaliphate.domain.post.CancelPostUploadUseCase
import com.bellogatecaliphate.domain.post.GetOngoingPostsUploadStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostScreenViewModel @Inject constructor(
	private val getOngoingPostsUploadStatusUseCase: GetOngoingPostsUploadStatusUseCase,
	private val cancelPostUploadUseCase: CancelPostUploadUseCase
) : ViewModel() {
	
	private val _state = MutableStateFlow(UiState())
	val state = _state.asStateFlow()
	
	init {
		observeOngoingPostUploads()
	}
	
	fun requestPermissionAndOpenGallery() {
		_state.update { it.copy(requestStoragePermissionAndOpenGallery = true) }
	}
	
	fun resetGalleryState() {
		_state.update { it.copy(requestStoragePermissionAndOpenGallery = false) }
	}
	
	fun cancelPostUpload(post: Post) = viewModelScope.launch {
		cancelPostUploadUseCase(post)
	}
	
	private fun observeOngoingPostUploads() = viewModelScope.launch {
		getOngoingPostsUploadStatusUseCase().collect { liveResults ->
			_state.update { it.copy(existingUploads = liveResults) }
		}
	}
}