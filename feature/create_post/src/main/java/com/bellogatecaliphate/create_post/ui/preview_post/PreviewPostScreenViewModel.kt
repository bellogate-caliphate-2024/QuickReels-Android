package com.bellogatecaliphate.create_post.ui.preview_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.ui.preview_post.model.PreviewPostUiState
import com.bellogatecaliphate.domain.post.GetVideoThumbnailUseCase
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class PreviewPostScreenViewModel @Inject constructor(
	private val getUserInfoUseCase: GetUserInfoUseCase,
	private val getVideoThumbnailUseCase: GetVideoThumbnailUseCase
) : ViewModel() {
	
	private val _state = MutableStateFlow(PreviewPostUiState())
	val state = _state.asStateFlow()
	
	fun validateVideoCaption(videoPath: String, captionText: String) = viewModelScope.launch {
		val validationResult = captionText.isNotEmpty() && captionText.isNotBlank()
		if (validationResult) {
			_state.update { it.copy(post = createPost(videoPath, captionText)) }
			_state.update { it.copy(showConfirmationBottomSheet = true) }
		} else {
			_state.update { it.copy(videoCaptionTextIsNotProvided = true) }
		}
	}
	
	private suspend fun createPost(videoPath: String, videoCaption: String): Post {
		return Post(
			videoFilePath = videoPath,
			userId = getUserInfoUseCase()?.id ?: "",
			time = LocalDateTime.now().toString(),
			caption = videoCaption,
			uploadProgressPercentage = "0",
			thumbnailBase64String = getVideoThumbnailUseCase(videoPath)
		)
	}
}