package com.bellogatecaliphate.core.common.ui.comments.model

data class CommentResponse(
	val commentId: String,
	val userId: String,
	val text: String,
	val date: String,
	val numberOfReplies: Int?
)