package com.bellogatecaliphate.contents.remote.api

import com.bellogatecaliphate.contents.remote.model.ContentsListResponse
import retrofit2.http.GET

interface ContentsApi {
	
	@GET("now_playing")
	suspend fun getContents(page: Int): ContentsListResponse
}