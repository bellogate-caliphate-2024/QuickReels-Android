package com.bellogatecaliphate.create_post.ui.confirm_post

import androidx.lifecycle.ViewModel
import com.bellogatecaliphate.core.model.dto.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadPostConfirmationViewModel @Inject constructor() : ViewModel() {
	
	fun enQueuePostForUpload(post: Post) {
	
	}
}