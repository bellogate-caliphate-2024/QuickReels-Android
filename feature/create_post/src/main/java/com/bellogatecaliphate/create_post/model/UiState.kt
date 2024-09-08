package com.bellogatecaliphate.create_post.model

sealed class UiState {

    object Default: UiState()
    object UploadingVide: UiState()
    object RequestStoragePermissionAndOpenGallery: UiState()
}