package com.bellogatecaliphate.contents

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bellogatecaliphate.contents.local.ILocalDataSource
import com.bellogatecaliphate.contents.paging.ContentsHistoryPagingSource
import com.bellogatecaliphate.contents.paging.ContentsRemoteMediator
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class ContentsRepository @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val remoteSource: IRemoteSource,
	private val localDataSource: ILocalDataSource,
	private val contentsHistoryPagingSource: ContentsHistoryPagingSource,
) : IContentsRepository {
	
	@OptIn(ExperimentalPagingApi::class)
	override fun getPaginatedContents(page: Int) = Pager(
		config = PagingConfig(pageSize = 10),
		remoteMediator = ContentsRemoteMediator(ioDispatcher, remoteSource, localDataSource)
	) {
		localDataSource.getPaginatedContents()
	}
	
	override fun getPaginatedContentsHistory(userEmail: String) =
			Pager(PagingConfig(pageSize = 20)) { contentsHistoryPagingSource }
	
}