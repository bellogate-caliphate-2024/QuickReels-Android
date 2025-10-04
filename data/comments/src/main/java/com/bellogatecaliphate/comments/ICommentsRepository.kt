package com.bellogatecaliphate.comments

import androidx.paging.PagingData
import com.bellogatecaliphate.comments.remote.model.AddCommentRequest
import com.bellogatecaliphate.comments.remote.model.CommentResponse
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
import com.bellogatecaliphate.comments.remote.model.SaveReplyToCommentResponse
import kotlinx.coroutines.flow.Flow

interface ICommentsRepository {
	
	fun getPaginatedComments(contentId: String): Flow<PagingData<CommentResponse>>
	
	suspend fun getCommentReplies(commentId: String, page: Int): CommentsListResponse?
	
	suspend fun saveReply(originalCommentId: String, reply: String): SaveReplyToCommentResponse?
	
	suspend fun deleteComment(commentId: String): Boolean
	
	suspend fun addComment(
		contentId: String,
		parentCommentId: String?,
		comment: AddCommentRequest
	): Boolean
	
}