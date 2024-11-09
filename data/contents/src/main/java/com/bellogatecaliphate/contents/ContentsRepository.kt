package com.bellogatecaliphate.contents

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bellogatecaliphate.contents.paging.ContentsRemoteMediator
import com.bellogatecaliphate.core.source.local.dao.ContentsDao
import javax.inject.Inject

internal class ContentsRepository @Inject constructor(
	private val contentsDao: ContentsDao,
	private val contentsRemoteMediator: ContentsRemoteMediator
) : IContentsRepository {
	
	@OptIn(ExperimentalPagingApi::class)
	override fun getPaginatedContents(page: Int) = Pager(
		config = PagingConfig(pageSize = 20),
		remoteMediator = contentsRemoteMediator
	) {
		contentsDao.getPaginatedContents(page)
	}
}