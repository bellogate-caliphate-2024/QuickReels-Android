package com.bellogatecaliphate.create_video.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellogatecaliphate.create_video.model.UiState

@Composable
fun VideoRecorderScreen(viewModel: VideoRecorderScreenViewModel = hiltViewModel()) {
    VideoRecorderScreen(UiState.Default)
}

@Composable
fun VideoRecorderScreen(uiState: UiState) {
    Text(text = "Create a post")
}

@Preview
@Composable
fun Preview() {
    VideoRecorderScreen()
}