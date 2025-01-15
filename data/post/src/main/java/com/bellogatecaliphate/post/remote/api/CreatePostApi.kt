package com.bellogatecaliphate.post.remote.api

import com.bellogatecaliphate.post.remote.model.CreatePostResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

internal interface CreatePostApi {
	
	@Multipart
	@POST("upload_video_file")
	suspend fun uploadPost(
		@Part videoFile: MultipartBody.Part,
		@Query("videoId") videoId: String,
		@Query("userId") userId: String,
		@Query("time") time: String,
		@Query("caption") caption: String,
	): CreatePostResponse?
}