package com.bellogatecaliphate.account.ui.screens.view_and_edit_content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellogatecaliphate.domain.contents.DeleteContentUseCase
import com.bellogatecaliphate.domain.contents.GetContentUseCase
import com.bellogatecaliphate.domain.contents.like.LikeContentUseCase
import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
internal class ViewAndEditContentScreenViewModel @Inject constructor(
	private val getLoggedInUserEmailUseCase: GetLoggedInUserEmailUseCase,
	private val getContentUseCase: GetContentUseCase,
	private val deleteContentUseCase: DeleteContentUseCase,
	private val likeContentUseCase: LikeContentUseCase,
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState = _uiState.asStateFlow()
	
	private val likeJobs = mutableMapOf<String, Job>()
	
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
		_uiState.update {
			it.copy(deleteContentSuccess = null, deleteContentInProgress = true)
		}
		val result = deleteContentUseCase(contentId)
		_uiState.update {
			it.copy(deleteContentSuccess = result, deleteContentInProgress = false)
		}
	}
	
	fun likeContent(contentId: String, isLiked: Boolean) = viewModelScope.launch {
		_uiState.update { current ->
			current.copy(
				likedContents = current.likedContents.toMutableMap().apply {
					this[contentId] = isLiked
				}
			)
		}
		likeJobs[contentId]?.cancel()
		val job = viewModelScope.launch {
			try {
				likeContentUseCase(contentId, isLiked)
			}
			catch (e: CancellationException) {
				// job was cancelled → ignore.
			}
			catch (e: Exception) {
				_uiState.update { current ->
					current.copy(
						likedContents = current.likedContents.toMutableMap().apply {
							this[contentId] = ! isLiked
						}
					)
				}
			}
		}
		
		likeJobs[contentId] = job
	}
}