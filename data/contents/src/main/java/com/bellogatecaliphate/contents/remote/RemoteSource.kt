package com.bellogatecaliphate.contents.remote

import com.bellogatecaliphate.contents.remote.api.ContentsApi
import com.bellogatecaliphate.contents.remote.model.ContentsListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteSource @Inject constructor(
	private val api: ContentsApi,
	private val ioDispatcher: CoroutineDispatcher
) : IRemoteSource {
	
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
	
	override suspend fun likeContent(userEmail: String, contentId: String, isLiked: Boolean) =
			withContext(ioDispatcher) {
				api.likeContent(userEmail, contentId, isLiked) !!.isSuccess
			}
}