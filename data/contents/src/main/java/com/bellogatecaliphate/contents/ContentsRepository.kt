package com.bellogatecaliphate.contents

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bellogatecaliphate.contents.paging.ContentsHistoryPagingSource
import com.bellogatecaliphate.contents.paging.ContentsPagingSource
import com.bellogatecaliphate.contents.remote.IRemoteSource
import javax.inject.Inject

internal class ContentsRepository @Inject constructor(
	private val remoteSource: IRemoteSource,
	private val contentsPagingSource: ContentsPagingSource,
	private val contentsHistoryPagingSource: ContentsHistoryPagingSource,
) : IContentsRepository {
	
	override fun getPaginatedContents(page: Int) = Pager(config = PagingConfig(pageSize = 10)) {
		contentsPagingSource
	}
	
	override fun getPaginatedContentsHistory() =
			Pager(PagingConfig(pageSize = 10)) { contentsHistoryPagingSource }
	
	override suspend fun likeContent(
		userEmail: String,
		contentId: String,
		isLiked: Boolean
	): Boolean {
		return remoteSource.likeContent(userEmail, contentId, isLiked)
	}
}