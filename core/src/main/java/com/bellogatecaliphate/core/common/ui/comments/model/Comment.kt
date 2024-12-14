package com.bellogatecaliphate.core.common.ui.comments.model

data class Comment(
	val commentId: String,
	val userId: String,
	val text: String,
	val date: String,
	val numberOfReplies: Int?,
	val isReply: Boolean? = false,
	val parentCommentId: String? = null
) {
	fun hasReplies() = numberOfReplies != null && numberOfReplies > 0
}