package com.bellogatecaliphate.comments.remote

import com.bellogatecaliphate.comments.remote.api.CommentsApi
import com.bellogatecaliphate.comments.remote.model.AddCommentRequest
import com.bellogatecaliphate.comments.remote.model.AddCommentResponse
import com.bellogatecaliphate.comments.remote.model.CommentResponse
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
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
	
	override suspend fun deleteComment(commentId: String): Boolean = withContext(ioDispatcher) {
		//return@withContext commentsApi.deleteComment(commentId)
		delay(3_000)
		true
	}
	
	override suspend fun addComment(
		comment: AddCommentRequest
	): AddCommentResponse = withContext(ioDispatcher) {
		delay(1_000)
		AddCommentResponse(true)
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
			"1", "", pic, "1st comment", "2025-10-02", 0,
		),
		CommentResponse(
			"2", "", pic, "2 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"3", "", pic, "3 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"4", "", pic, "4 comment", "2025-10-02", 0,
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
			"5", "", pic, "5 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"6", "", pic, "6 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"7", "", pic, "7 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"8", "", pic, "8 comment", "2025-10-02", 0,
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
			"9", "jeffemuveyan@gmail.com", pic, "9 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"10", "", pic, "10 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"11", "jeffemuveyan@gmail.com", pic, "11 comment", "2025-10-02", 8,
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
			"12", "", pic, "12 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"13", "", pic, "13 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"14", "", pic, "14 comment", "2025-10-02", 0,
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
			"15", "", pic, "15 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"16", "", pic, "16 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"17", "", pic, "17 comment", "2025-10-02", 0,
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
			"18", "", pic, "18 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"19", "", pic, "19 comment", "2025-10-02", 0,
		),
		CommentResponse(
			"20", "", pic, "20 comment", "2025-10-02", 0,
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
			"r1", "jeffemuveyan@gmail.com", pic, "replies 1", "2025-10-02", 0,
			parentCommentId = "11"
		),
		CommentResponse(
			"r2", "jeffemuveyan@gmail.com", pic, "replies 2", "2025-10-02", 0,
			parentCommentId = "11"
		),
		CommentResponse(
			"r3", "jeffemuveyan@gmail.com", pic, "replies 3", "2025-10-02", 0,
			parentCommentId = "11"
		),
		CommentResponse(
			"r4", "jeffemuveyan@gmail.com", pic, "replies 4", "2025-10-02", 0,
			parentCommentId = "11"
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
			"r5", "jeffemuveyan@gmail.com", pic, "replies 5", "2025-10-02", 0,
			parentCommentId = "11"
		),
		CommentResponse(
			"r6", "jeffemuveyan@gmail.com", pic, "replies 6", "2025-10-02", 0,
			parentCommentId = "11"
		),
		CommentResponse(
			"r7", "jeffemuveyan@gmail.com", pic, "replies 7", "2025-10-02", 0,
			parentCommentId = "11"
		),
		CommentResponse(
			"r8", "jeffemuveyan@gmail.com", pic, "replies 8", "2025-10-02", 0,
			parentCommentId = "11"
		)
	)
)