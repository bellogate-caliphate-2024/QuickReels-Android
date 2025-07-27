package com.bellogatecaliphate.comments

import androidx.paging.Pager
import com.bellogatecaliphate.comments.remote.model.CommentResponse
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
import com.bellogatecaliphate.comments.remote.model.SaveReplyToCommentResponse

interface ICommentsRepository {
	
	fun getPaginatedComments(contentId: String): Pager<Int, CommentResponse>
	
	suspend fun getCommentReplies(commentId: String, page: Int): CommentsListResponse?
	
	suspend fun saveReply(originalCommentId: String, reply: String): SaveReplyToCommentResponse?
	
	suspend fun deleteComment(commentId: String): Boolean
}