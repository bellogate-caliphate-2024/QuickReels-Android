package com.bellogatecaliphate.contents.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bellogatecaliphate.core.source.local.entity.ContentsEntity

@OptIn(ExperimentalPagingApi::class)
class ContentsRemoteMediator : RemoteMediator<Int, ContentsEntity>() {
	
	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, ContentsEntity>
	): MediatorResult {
		TODO("Not yet implemented")
	}
}