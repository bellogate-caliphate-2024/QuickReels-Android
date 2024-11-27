package com.bellogatecaliphate.contents.local

import androidx.paging.PagingSource
import com.bellogatecaliphate.core.source.local.entity.ContentEntity
import com.bellogatecaliphate.core.source.local.entity.ContentsListPageInfoEntity

interface ILocalDataSource {
	
	suspend fun deleteAllContents()
	
	suspend fun deleteAllContentsListPageInfo()
	
	fun getPaginatedContents(): PagingSource<Int, ContentEntity>
	
	suspend fun getContentsListPageInfo(): ContentsListPageInfoEntity?
	
	suspend fun insert(contentsListPageInfoEntity: ContentsListPageInfoEntity)
	
	suspend fun insert(list: List<ContentEntity>)
}