package com.bellogatecaliphate.contents

import androidx.paging.Pager
import com.bellogatecaliphate.contents.remote.model.ContentResponse

interface IContentsRepository {
	fun getPaginatedContents(page: Int): Pager<Int, ContentResponse>
	fun getPaginatedContentsHistory(userEmail: String): Pager<Int, ContentResponse>
	suspend fun likeContent(userEmail: String, contentId: String, isLiked: Boolean): Boolean
	suspend fun deleteContent(userEmail: String, contentId: String): Boolean
	suspend fun editContentCaption(
		userEmail: String,
		contentId: String,
		newCaption: String
	): Boolean
}