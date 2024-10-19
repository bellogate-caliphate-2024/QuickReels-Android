package com.bellogatecaliphate.create_post.ui.confirm_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.domain.post.EnQueuePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadPostConfirmationViewModel @Inject constructor(
	private val enqueuePostUseCase: EnQueuePostUseCase
) : ViewModel() {
	
	fun enQueuePostForUpload(post: Post) = viewModelScope.launch {
		enqueuePostUseCase(post)
	}
}