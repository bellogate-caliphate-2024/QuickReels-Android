package com.bellogatecaliphate.contents.local

import androidx.paging.PagingSource
import com.bellogatecaliphate.core.source.local.dao.ContentsDao
import com.bellogatecaliphate.core.source.local.dao.ContentsListPageInfoDao
import com.bellogatecaliphate.core.source.local.entity.ContentEntity
import com.bellogatecaliphate.core.source.local.entity.ContentsListPageInfoEntity
import javax.inject.Inject

internal class LocalDataSource @Inject constructor(
	private val contentsDao: ContentsDao,
	private val contentsListPageInfoDao: ContentsListPageInfoDao
) : ILocalDataSource {
	
	override suspend fun deleteAllContents() {
		contentsDao.deleteAll()
	}
	
	override suspend fun deleteAllContentsListPageInfo() {
		contentsListPageInfoDao.deleteAll()
	}
	
	override fun getPaginatedContents(): PagingSource<Int, ContentEntity> {
		return contentsDao.getPaginatedContents()
	}
	
	override suspend fun getContentsListPageInfo(): ContentsListPageInfoEntity? {
		return contentsListPageInfoDao.getContentsListPage()
	}
	
	override suspend fun insert(contentsListPageInfoEntity: ContentsListPageInfoEntity) {
		contentsListPageInfoDao.insert(contentsListPageInfoEntity)
	}
	
	override suspend fun insert(list: List<ContentEntity>) {
		contentsDao.insert(list)
	}
}