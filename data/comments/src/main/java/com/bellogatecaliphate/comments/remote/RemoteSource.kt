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
	
	override suspend fun getCommentReplies(
		commentId: String,
		page: Int
	): CommentsListResponse = withContext(ioDispatcher) {
		//commentsApi.getCommentReplies(commentId, page)
		delay(4_000)
		getReplies(page)
	}
	
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
		4    -> commentsFour
		5    -> commentsFive
		else -> {
			commentsSix
		}
	}
}

val pic =
		"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/p1.jpg?alt=media&token=630134db-35cb-41ea-9698-fa0cd25b80c0"

val commentsOne = CommentsListResponse(
	"11",
	1,
	2,
	false,
	listOf(
		CommentResponse(
			"1",
			"jeffemuveyan@gmail.com",
			pic,
			"FIRST Hahaha, I laughed so hard mehn! So this film was produced very early on may 2021 before the cannes film festival. I loved it so much because it talked about so many things.",
			"2025-10-02",
			0,
		),
		CommentResponse(
			"2", "", pic, "FIRST Will u see the film?", "2025-10-02", 0,
		),
		CommentResponse(
			"3", "", pic, "FIRST I loved this movie", "2025-10-02", 0,
		),
		CommentResponse(
			"4", "jeffemuveyan@gmail.com", pic, "This is a bizare comment", "2025-10-02", 0,
		)
	)
)

val commentsTwo = CommentsListResponse(
	"22",
	2,
	3,
	false,
	listOf(
		CommentResponse(
			"5", "", pic, "SECOND comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"6", "", pic, "SECOND scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"7", "", pic, "SECOND u must be joking", "2025-10-02", 0,
		),
		CommentResponse(
			"8", "", pic, "SECOND I thinnk it is boring", "2025-10-02", 2,
		)
	)
)

val commentsThree = CommentsListResponse(
	"33",
	3,
	4,
	false,
	listOf(
		CommentResponse(
			"9", "", pic, "THIRD comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"10", "", pic, "THIRD scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"11", "", pic, "THIRD u must be joking", "2025-10-02", 0,
		)
	)
)

val commentsFour = CommentsListResponse(
	"44",
	4,
	5,
	false,
	listOf(
		CommentResponse(
			"12", "", pic, "THIRD comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"13", "", pic, "THIRD scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"14", "", pic, "THIRD u must be joking", "2025-10-02", 0,
		)
	)
)

val commentsFive = CommentsListResponse(
	"55",
	5,
	6,
	false,
	listOf(
		CommentResponse(
			"1255", "", pic, "555 comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"1355", "", pic, "555 scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"1455", "", pic, "555 u must be joking", "2025-10-02", 0,
		)
	)
)

val commentsSix = CommentsListResponse(
	"66",
	6,
	null,
	true,
	listOf(
		CommentResponse(
			"1266", "", pic, "66 comment here bro", "2025-10-02", 0,
		),
		CommentResponse(
			"1366", "", pic, "66 scary movie! Dont watch alone", "2025-10-02", 0,
		),
		CommentResponse(
			"1466", "", pic, "66 u must be joking", "2025-10-02", 0,
		)
	)
)

fun getReplies(page: Int): CommentsListResponse {
	return when (page) {
		1    -> repliesBatchOne
		2    -> repliesBatchTwo
		else -> {
			repliesBatchOne
		}
	}
}

val repliesBatchOne = CommentsListResponse(
	"11",
	1,
	2,
	false,
	listOf(
		CommentResponse(
			"1",
			"jeffemuveyan@gmail.com",
			pic,
			"FIRST Hahaha, I laughed so hard mehn! So this film was produced very early on may 2021 before the cannes film festival. I loved it so much because it talked about so many things.",
			"2025-10-02",
			0,
			parentCommentId = "8"
		),
		CommentResponse(
			"2", "", pic, "FIRST Will u see the film?", "2025-10-02", 0,
			parentCommentId = "8"
		),
		CommentResponse(
			"3", "", pic, "FIRST I loved this movie", "2025-10-02", 0,
			parentCommentId = "8"
		),
		CommentResponse(
			"4", "jeffemuveyan@gmail.com", pic, "This is a bizare comment", "2025-10-02", 0,
			parentCommentId = "8"
		)
	)
)

val repliesBatchTwo = CommentsListResponse(
	"11",
	2,
	null,
	true,
	listOf(
		CommentResponse(
			"1",
			"jeffemuveyan@gmail.com",
			pic,
			"FIRST Hahaha, I laughed so hard mehn! So this film was produced very early on may 2021 before the cannes film festival. I loved it so much because it talked about so many things.",
			"2025-10-02",
			0,
			parentCommentId = "8"
		),
		CommentResponse(
			"2", "", pic, "second replies text", "2025-10-02", 0,
			parentCommentId = "8"
		),
		CommentResponse(
			"3", "", pic, "second replies text message here", "2025-10-02", 0,
			parentCommentId = "8"
		),
		CommentResponse(
			"4", "jeffemuveyan@gmail.com", pic, "This is the last replies", "2025-10-02", 0,
			parentCommentId = "8"
		)
	)
)