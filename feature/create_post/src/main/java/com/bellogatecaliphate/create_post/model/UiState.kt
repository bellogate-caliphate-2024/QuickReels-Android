package com.bellogatecaliphate.create_post.model

import com.bellogatecaliphate.core.model.dto.Post

data class UiState(
	val requestStoragePermissionAndOpenGallery: Boolean = false,
	val existingUploads: List<Post> = emptyList(),
	val isLoading: Boolean = false
)