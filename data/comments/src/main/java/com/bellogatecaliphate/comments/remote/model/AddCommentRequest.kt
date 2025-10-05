package com.bellogatecaliphate.comments.remote.model

data class AddCommentRequest(
	val contentId: String,
	val commentId: String,
	val userId: String,
	val text: String,
	val parentCommentId: String?
)