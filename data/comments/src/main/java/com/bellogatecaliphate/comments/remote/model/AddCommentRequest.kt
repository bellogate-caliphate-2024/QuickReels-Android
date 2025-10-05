package com.bellogatecaliphate.comments.remote.model

data class AddCommentRequest(
	val commentId: String,
	val userId: String,
	val text: String,
	val isReply: Boolean?,
	val parentCommentId: String?
)