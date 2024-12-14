package com.bellogatecaliphate.core.ui.comments.model

data class CommentResponse(
	val commentId: String,
	val userId: String,
	val text: String,
	val date: String,
	val numberOfReplies: Int?
)