package com.bellogatecaliphate.core.source.local.dao.di

import com.bellogatecaliphate.core.source.local.AppDatabase
import com.bellogatecaliphate.core.source.local.dao.PostDao
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
abstract class PostDaoModule {
	
	class PostDaoImp @Inject constructor(private val appDatabase: AppDatabase) : PostDao {
		
		override suspend fun insert(postEntity: PostEntity) {
			appDatabase.postDao().insert(postEntity)
		}
		
		override suspend fun delete(postEntity: PostEntity) {
			appDatabase.postDao().delete(postEntity)
		}
		
		override fun getPostsByStatus(status: PostEntity.Status): Flow<List<PostEntity>> {
			return appDatabase.postDao().getPostsByStatus(status)
		}
		
		override fun getPostById(id: String): PostEntity? {
			return appDatabase.postDao().getPostById(id)
		}
	}
	
	@Binds
	abstract fun bindPostDao(impl: PostDaoImp): PostDao
}