package com.bellogatecaliphate.comments.remote.api

import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CommentsApi {
	
	@GET("get_comments")
	suspend fun getComments(
		@Query("contentId") contentId: String,
		@Query("page") page: Int,
		@Query("numberOfCommentsPerPage") numberOfCommentsPerPage: Int
	): CommentsListResponse?
	
	@GET("get_comment_replies")
	suspend fun getCommentReplies(
		@Query("commentId") commentId: String,
		@Query("page") page: Int
	): CommentsListResponse?
	
	@DELETE
	suspend fun deleteComment(@Query("commentId") commentId: String): Boolean
}