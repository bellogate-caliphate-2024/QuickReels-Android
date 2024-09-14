package com.bellogatecaliphate.create_post.model

sealed class UiState {

    data object Default: UiState()
    data object PreviewPost: UiState()
    data object RequestStoragePermissionAndOpenGallery: UiState()
}