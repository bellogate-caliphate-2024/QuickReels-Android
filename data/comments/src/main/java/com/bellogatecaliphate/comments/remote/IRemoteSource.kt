package com.bellogatecaliphate.comments.remote

import com.bellogatecaliphate.comments.remote.model.AddCommentRequest
import com.bellogatecaliphate.comments.remote.model.AddCommentResponse
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse

internal interface IRemoteSource {
	
	suspend fun getComments(
		contentId: String,
		page: Int,
		numberOfCommentsPerPage: Int
	): CommentsListResponse?
	
	suspend fun getCommentReplies(commentId: String, page: Int): CommentsListResponse?
	
	suspend fun deleteComment(commentId: String): Boolean
	
	suspend fun addComment(
		comment: AddCommentRequest
	): AddCommentResponse?
}