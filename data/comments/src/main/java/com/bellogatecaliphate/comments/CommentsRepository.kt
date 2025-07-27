package com.bellogatecaliphate.comments

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bellogatecaliphate.comments.paging.CommentsPagingSource
import com.bellogatecaliphate.comments.remote.IRemoteSource
import com.bellogatecaliphate.comments.remote.model.CommentResponse
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
import com.bellogatecaliphate.comments.remote.model.SaveReplyToCommentResponse
import javax.inject.Inject

internal class CommentsRepository @Inject constructor(
	private val commentsPagingSource: CommentsPagingSource,
	private val remoteSource: IRemoteSource
) : ICommentsRepository {
	
	override fun getPaginatedComments(contentId: String): Pager<Int, CommentResponse> =
			Pager(PagingConfig(pageSize = 4)) {
				commentsPagingSource.also {
					it.contentId = contentId
				}
			}
	
	override suspend fun getCommentReplies(commentId: String, page: Int): CommentsListResponse? {
		return remoteSource.getCommentReplies(commentId, page)
	}
	
	override suspend fun saveReply(
		originalCommentId: String,
		reply: String
	): SaveReplyToCommentResponse? {
		return remoteSource.saveReply(originalCommentId, reply)
	}
	
	override suspend fun deleteComment(commentId: String): Boolean {
		return remoteSource.deleteComment(commentId)
	}
}