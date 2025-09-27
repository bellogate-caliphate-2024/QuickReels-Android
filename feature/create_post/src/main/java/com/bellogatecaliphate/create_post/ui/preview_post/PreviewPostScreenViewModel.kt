package com.bellogatecaliphate.create_post.ui.preview_post

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.ui.preview_post.model.PreviewPostUiState
import com.bellogatecaliphate.domain.GetDateUseCase
import com.bellogatecaliphate.domain.post.EnQueuePostUseCase
import com.bellogatecaliphate.domain.post.GetVideoThumbnailUseCase
import com.bellogatecaliphate.domain.post.SaveVideoThumbnailFileUseCase
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviewPostScreenViewModel @Inject constructor(
	private val getUserInfoUseCase: GetUserInfoUseCase,
	private val saveVideoThumbnailFileUseCase: SaveVideoThumbnailFileUseCase,
	private val getVideoThumbnailUseCase: GetVideoThumbnailUseCase,
	private val enqueuePostUseCase: EnQueuePostUseCase,
	private val getDateUseCase: GetDateUseCase
) : ViewModel() {
	
	private val _state = MutableStateFlow(PreviewPostUiState())
	val state = _state.asStateFlow()
	
	fun validateVideoCaption(videoPath: String, captionText: String) = viewModelScope.launch {
		_state.update { it.copy(isLoading = true, videoCaptionTextIsNotProvided = false) }
		
		val isCaptionAvailable = captionText.isNotEmpty() && captionText.isNotBlank()
		if (isCaptionAvailable) {
			_state.update {
				it.copy(
					isLoading = false,
					showConfirmationBottomSheet = true,
					videoCaptionTextIsNotProvided = false,
					post = createPost(videoPath, captionText)
				)
			}
		} else {
			_state.update { it.copy(isLoading = false, videoCaptionTextIsNotProvided = true) }
		}
	}
	
	fun enQueuePostForUpload(post: Post) = viewModelScope.launch {
		enqueuePostUseCase(post)
	}
	
	fun onConfirmationDialogDismissed() {
		_state.update { it.copy(showConfirmationBottomSheet = false) }
	}
	
	private suspend fun createPost(videoPath: String, videoCaption: String): Post {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			Post(
				videoFilePath = videoPath,
				userId = getUserInfoUseCase()?.email ?: "",
				time = getDateUseCase(),
				caption = videoCaption,
				uploadProgressPercentage = "0",
				thumbnailFilePath = saveVideoThumbnailFileUseCase(getVideoThumbnailUseCase(videoPath))
			)
		} else {
			TODO("This version of Android is not supported for this app")
		}
	}
}