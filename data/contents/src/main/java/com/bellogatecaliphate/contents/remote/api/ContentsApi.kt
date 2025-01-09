package com.bellogatecaliphate.contents.remote.api

import com.bellogatecaliphate.contents.remote.model.ContentsListResponse
import com.bellogatecaliphate.contents.remote.model.LikeContentResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface ContentsApi {
	
	@GET("now_playing")
	suspend fun getContentsList(
		@Query("page") page: Int?,
		@Query("numberOfContentPerPage") numberOfContentPerPage: Int = 10
	): ContentsListResponse?
	
	@POST("like_content")
	suspend fun likeContent(
		@Query("userEmail") userEmail: String,
		@Query("contentId") contentId: String,
		@Query("contentId") isLiked: Boolean
	): LikeContentResponse?
}