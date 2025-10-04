package com.bellogatecaliphate.comments.remote.model

data class AddCommentRequest(
	val commentId: String,
	val userId: String,
	val userProfilePictureUrl: String,
	val text: String,
	val date: String,
	val numberOfReplies: Int?,
	val isReply: Boolean?,
	val parentCommentId: String?
)