package com.bellogatecaliphate.contents

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bellogatecaliphate.contents.paging.ContentsPagingSource
import com.bellogatecaliphate.contents.paging.di.ContentsHistoryPagingSourceFactory
import com.bellogatecaliphate.contents.remote.IRemoteSource
import com.bellogatecaliphate.contents.remote.model.ContentResponse
import javax.inject.Inject

/**
 * Because we need to pass in the userEmail to the paging source, we will user a factory to inject
 * this ContentsHistoryPagingSource.
 **/
internal class ContentsRepository @Inject constructor(
	private val remoteSource: IRemoteSource,
	private val contentsPagingSource: ContentsPagingSource,
	private val contentsHistoryPagingSourceFactory: ContentsHistoryPagingSourceFactory,
) : IContentsRepository {
	
	override fun getPaginatedContents(page: Int) = Pager(config = PagingConfig(pageSize = 10)) {
		contentsPagingSource
	}
	
	override suspend fun getContent(contentId: String): ContentResponse? {
		return remoteSource.getContent(contentId)
	}
	
	override fun getPaginatedContentsHistory(userEmail: String) =
			Pager(PagingConfig(pageSize = 10)) { contentsHistoryPagingSourceFactory.create(userEmail) }
	
	override suspend fun likeContent(
		userEmail: String,
		contentId: String,
		isLiked: Boolean
	): Boolean {
		return remoteSource.likeContent(userEmail, contentId, isLiked)
	}
	
	override suspend fun deleteContent(userEmail: String, contentId: String): Boolean {
		return remoteSource.deleteContent(userEmail, contentId)
	}
	
	override suspend fun editContentCaption(
		userEmail: String,
		contentId: String,
		newCaption: String
	): Boolean {
		return remoteSource.editContentCaption(userEmail, contentId, newCaption)
	}
}