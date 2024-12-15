package com.bellogatecaliphate.core.source.local.dao.di

import androidx.paging.PagingSource
import com.bellogatecaliphate.core.source.local.AppDatabase
import com.bellogatecaliphate.core.source.local.dao.ContentsDao
import com.bellogatecaliphate.core.source.local.entity.ContentEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class)
abstract class ContentsDaoModule {
	
	class ContentsDaoImp @Inject constructor(private val appDatabase: AppDatabase) : ContentsDao {
		
		override fun getPaginatedContents(): PagingSource<Int, ContentEntity> {
			return appDatabase.contentsDao().getPaginatedContents()
		}
		
		override suspend fun deleteAll() {
			return appDatabase.contentsDao().deleteAll()
		}
		
		override suspend fun insert(list: List<ContentEntity>) {
			appDatabase.contentsDao().insert(list)
		}
		
	}
	
	@Binds
	abstract fun bindContentsDao(impl: ContentsDaoImp): ContentsDao
}