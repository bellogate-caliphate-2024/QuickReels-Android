package com.bellogatecaliphate.contents

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bellogatecaliphate.contents.local.ILocalDataSource
import com.bellogatecaliphate.contents.paging.ContentsRemoteMediator
import javax.inject.Inject

internal class ContentsRepository @Inject constructor(
	private val localDataSource: ILocalDataSource,
	private val contentsRemoteMediator: ContentsRemoteMediator
) : IContentsRepository {
	
	@OptIn(ExperimentalPagingApi::class)
	override fun getPaginatedContents(page: Int) = Pager(
		config = PagingConfig(pageSize = 10),
		remoteMediator = contentsRemoteMediator
	) {
		localDataSource.getPaginatedContents()
	}
}