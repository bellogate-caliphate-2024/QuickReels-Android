package com.bellogatecaliphate.comments

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bellogatecaliphate.comments.paging.CommentsPagingSource
import com.bellogatecaliphate.comments.remote.IRemoteSource
import com.bellogatecaliphate.comments.remote.model.AddCommentRequest
import com.bellogatecaliphate.comments.remote.model.CommentResponse
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class CommentsRepository @Inject constructor(
	private val commentsPagingSource: CommentsPagingSource,
	private val remoteSource: IRemoteSource
) : ICommentsRepository {
	
	override fun getPaginatedComments(contentId: String): Flow<PagingData<CommentResponse>> =
			Pager(PagingConfig(pageSize = 4)) {
				commentsPagingSource.also {
					it.contentId = contentId
				}
			}.flow
	
	override suspend fun getCommentReplies(commentId: String, page: Int): CommentsListResponse? {
		return remoteSource.getCommentReplies(commentId, page)
	}
	
	override suspend fun deleteComment(commentId: String): Boolean {
		return remoteSource.deleteComment(commentId)
	}
	
	override suspend fun addComment(
		comment: AddCommentRequest
	): Boolean {
		val result = remoteSource.addComment(comment)
		return result?.isSuccessful ?: false
	}
}