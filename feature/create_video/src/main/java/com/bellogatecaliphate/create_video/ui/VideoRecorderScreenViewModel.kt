package com.bellogatecaliphate.create_video.ui

import androidx.lifecycle.ViewModel
import com.bellogatecaliphate.create_video.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VideoRecorderScreenViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Default)
    val state = _state.asStateFlow()

    fun requestPermissionAndOpenGallery() {
        _state.value = UiState.RequestStoragePermissionAndOpenGallery
    }

    fun resetToDefault() {
        _state.value = UiState.Default
    }

}