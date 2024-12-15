package com.bellogatecaliphate.contents.remote

import com.bellogatecaliphate.contents.remote.model.ContentsListResponse

internal interface IRemoteSource {
	
	suspend fun getContentsList(page: Int?, numberOfContentPerPage: Int = 10): ContentsListResponse?
	
	suspend fun getContentsHistoryList(
		userEmail: String,
		page: Int,
		numberOfContentPerPage: Int = 10
	): ContentsListResponse?
}