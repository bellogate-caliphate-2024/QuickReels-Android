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
	
	override suspend fun deleteComment(commentId: String): Boolean = withContext(ioDispatcher) {
		//return@withContext commentsApi.deleteComment(commentId)
		delay(3_000)
		true
	}
}

fun getComments(page: Int): CommentsListResponse {
	return when (page) {
		1    -> commentsOne
		2    -> commentsTwo
		3    -> commentsThree
		else -> {
			commentsFour
		}
	}
}

val pic =
		"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/p1.jpg?alt=media&token=630134db-35cb-41ea-9698-fa0cd25b80c0"

val commentsOne = CommentsListResponse(
	"",
	1,
	2,
	false,
	listOf(
		CommentResponse(
			"",
			"jeffemuveyan@gmail.com",
			pic,
			"FIRST Hahaha, I laughed so hard mehn! So this film was produced very early on may 2021 before the cannes film festival. I loved it so much because it talked about so many things.",
			"2025-10-02",
			0,
		),
		CommentResponse(
			"", "", pic, "FIRST Will u see the film?", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "FIRST I loved this movie", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "FIRST Is it worth the wait, guys?", "2025-10-02", 0,
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
			"", "", pic, "SECOND comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "SECOND scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "SECOND u must be joking", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "SECOND I thinnk it is boring", "2025-10-02", 2,
		)
	)
)

val commentsThree = CommentsListResponse(
	"",
	3,
	4,
	false,
	listOf(
		CommentResponse(
			"", "", pic, "THIRD comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "THIRD scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "THIRD u must be joking", "2025-10-02", 0,
		)
	)
)

val commentsFour = CommentsListResponse(
	"",
	4,
	null,
	true,
	listOf(
		CommentResponse(
			"", "", pic, "THIRD comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "THIRD scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"", "", pic, "THIRD u must be joking", "2025-10-02", 0,
		)
	)
)