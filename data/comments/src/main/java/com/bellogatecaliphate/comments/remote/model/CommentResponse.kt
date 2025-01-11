package com.bellogatecaliphate.comments.remote.model

data class CommentResponse(
	val commentId: String,
	val userId: String,
	val text: String,
	val date: String,
	val numberOfReplies: Int?,
	val isReply: Boolean? = false,
	val parentCommentId: String? = null
)