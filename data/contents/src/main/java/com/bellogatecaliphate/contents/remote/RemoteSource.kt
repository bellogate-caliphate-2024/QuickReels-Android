package com.bellogatecaliphate.contents.remote

import com.bellogatecaliphate.contents.remote.api.ContentsApi
import com.bellogatecaliphate.contents.remote.model.ContentsListResponse
import javax.inject.Inject

internal class RemoteSource @Inject constructor(val api: ContentsApi) : IRemoteSource {
	
	override suspend fun getContentsList(
		page: Int?,
		numberOfContentPerPage: Int
	): ContentsListResponse? {
		return api.getContentsList(page, numberOfContentPerPage)
	}
	
	override suspend fun getContentsHistoryList(
		userEmail: String,
		page: Int,
		numberOfContentPerPage: Int
	): ContentsListResponse? {
		return null
	}
}