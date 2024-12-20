package com.bellogatecaliphate.create_post.model

import com.bellogatecaliphate.core.model.dto.Post

data class UiState(
	val requestStoragePermissionAndOpenGallery: Boolean = false,
	val uploadsInProgress: List<Post> = emptyList()
)