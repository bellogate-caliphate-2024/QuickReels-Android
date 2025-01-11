package com.bellogatecaliphate.comments.remote

import com.bellogatecaliphate.comments.remote.api.CommentsApi
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
import com.bellogatecaliphate.comments.remote.model.SaveReplyToCommentResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteSource @Inject constructor(
	private val commentsApi: CommentsApi,
	private val ioDispatcher: CoroutineDispatcher,
) : IRemoteSource {
	
	override suspend fun getComments(
		contentId: String,
		page: Int,
		numberOfCommentsPerPage: Int
	): CommentsListResponse? = withContext(ioDispatcher) {
		commentsApi.getComments(contentId, page, numberOfCommentsPerPage)
	}
	
	override suspend fun getCommentReplies(commentId: String, page: Int): CommentsListResponse? =
			withContext(ioDispatcher) { commentsApi.getCommentReplies(commentId, page) }
	
	override suspend fun saveReply(
		originalCommentId: String,
		reply: String
	): SaveReplyToCommentResponse? = withContext(ioDispatcher) {
		commentsApi.saveReply(originalCommentId, reply)
	}
}