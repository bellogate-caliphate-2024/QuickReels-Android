package com.bellogatecaliphate.create_post.ui.preview_post.model

import com.bellogatecaliphate.core.model.dto.Post

data class PreviewPostUiState(
	val showConfirmationBottomSheet: Boolean = false,
	val videoCaptionTextIsNotProvided: Boolean = false,
	val dismiss: Boolean = false,
	val post: Post? = null
)