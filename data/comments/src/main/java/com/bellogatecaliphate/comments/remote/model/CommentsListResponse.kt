package com.bellogatecaliphate.comments.remote.model

data class CommentsListResponse(
	val contentId: String,
	val currentPage: Int? = null,
	val nextPage: Int? = null,
	val comments: List<CommentResponse>? = null
)