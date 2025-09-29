package com.bellogatecaliphate.create_post.model

import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.core.model.dto.User

data class UiState(
	val user: User? = null,
	val networkError: Boolean = false,
	val isUserLoggedIn: Boolean = false,
	val requestStoragePermissionAndOpenGallery: Boolean = false,
	val existingUploads: List<Post> = emptyList(),
	val isLoading: Boolean = false
)