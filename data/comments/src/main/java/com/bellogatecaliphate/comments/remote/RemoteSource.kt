package com.bellogatecaliphate.comments.remote

import com.bellogatecaliphate.comments.remote.api.CommentsApi
import com.bellogatecaliphate.comments.remote.model.CommentResponse
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
import com.bellogatecaliphate.comments.remote.model.SaveReplyToCommentResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
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
	): CommentsListResponse = withContext(ioDispatcher) {
		delay(4_000)
		getComments(page)
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

fun getComments(page: Int): CommentsListResponse {
	return when (page) {
		1    -> commentsOne
		2    -> commentsTwo
		else -> {
			commentsThree
		}
	}
}

val commentsOne = CommentsListResponse(
	"",
	1,
	2,
	false,
	listOf(
		CommentResponse(
			"", "", "FIRST Hahaha, I laughed so hard mehn!", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", "FIRST Will u see the film?", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", "FIRST I loved this movie", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", "FIRST Is it worth the wait, guys?", "2025-10-02", 0,
		)
	)
)

val commentsTwo = CommentsListResponse(
	"",
	2,
	3,
	false,
	listOf(
		CommentResponse(
			"", "", "SECOND comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", "SECOND scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", "SECOND u must be joking", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", "SECOND I thinnk it is boring", "2025-10-02", 2,
		)
	)
)

val commentsThree = CommentsListResponse(
	"",
	3,
	null,
	true,
	listOf(
		CommentResponse(
			"", "", "THIRD comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", "THIRD scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", "THIRD u must be joking", "2025-10-02", 0,
		)
	)
)