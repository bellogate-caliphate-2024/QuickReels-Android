package com.bellogatecaliphate.core.source.local.dao.di

import com.bellogatecaliphate.core.source.local.AppDatabase
import com.bellogatecaliphate.core.source.local.dao.ContentsListPageInfoDao
import com.bellogatecaliphate.core.source.local.entity.ContentsListPageInfoEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
abstract class ContentsListPageInfoDaoModule {
	
	class ContentsListPageInfoDaoImp @Inject constructor(private val appDatabase: AppDatabase) :
			ContentsListPageInfoDao {
		
		override suspend fun getContentsListPage(): ContentsListPageInfoEntity? {
			return appDatabase.contentsListPageInfoDao().getContentsListPage()
		}
		
		override suspend fun insert(contentsListPageInfoEntity: ContentsListPageInfoEntity) {
			return appDatabase.contentsListPageInfoDao().insert(contentsListPageInfoEntity)
		}
		
		override suspend fun deleteAll() {
			return appDatabase.contentsListPageInfoDao().deleteAll()
		}
	}
	
	@Binds
	abstract fun bindContentsDao(impl: ContentsListPageInfoDaoImp): ContentsListPageInfoDao
}