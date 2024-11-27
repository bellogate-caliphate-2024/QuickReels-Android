package com.bellogatecaliphate.contents.remote

import com.bellogatecaliphate.contents.remote.model.ContentsListResponse

internal class RemoteSource : IRemoteSource {
	
	override suspend fun getContentsList(
		page: Int?,
		numberOfContentPerPage: Int
	): ContentsListResponse? {
		TODO("Not yet implemented")
	}
}