package com.bellogatecaliphate.core.ui.comments.model

data class CommentsListResponse(
	val contentId: String,
	val currentPage: Int? = null,
	val nextPage: Int? = null,
	val comments: List<CommentResponse>? = null
)