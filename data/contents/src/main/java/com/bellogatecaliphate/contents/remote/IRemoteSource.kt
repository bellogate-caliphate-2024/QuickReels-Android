package com.bellogatecaliphate.contents.remote

import com.bellogatecaliphate.contents.remote.model.ContentResponse
import com.bellogatecaliphate.contents.remote.model.ContentsListResponse

internal interface IRemoteSource {
	
	suspend fun getContentsList(page: Int?, numberOfContentPerPage: Int = 10): ContentsListResponse?
	
	suspend fun getContentsHistoryList(
		userEmail: String,
		page: Int,
		numberOfContentPerPage: Int = 10
	): ContentsListResponse?
	
	suspend fun getContent(contentId: String): ContentResponse?
	
	suspend fun likeContent(userEmail: String, contentId: String, isLiked: Boolean): Boolean
	
	suspend fun deleteContent(userEmail: String, contentId: String): Boolean
	
	suspend fun editContentCaption(
		userEmail: String,
		contentId: String,
		newCaption: String
	): Boolean
}