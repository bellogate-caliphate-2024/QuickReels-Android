package com.bellogatecaliphate.post.local

import com.bellogatecaliphate.core.source.database.dao.PostDao
import com.bellogatecaliphate.core.source.database.entity.PostEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PostLocalDataSource @Inject constructor(
	private val postDao: PostDao,
	private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPostLocalDataSource {
	
	override suspend fun savePost(postEntity: PostEntity) = withContext(ioDispatcher) {
		postDao.insert(postEntity)
	}
	
	override suspend fun deletePost(postEntity: PostEntity) = withContext(ioDispatcher) {
		postDao.insert(postEntity)
	}
	
	override suspend fun getPostById(postId: String): PostEntity? {
		return postDao.getPostById(postId)
	}
	
	override fun getPostsByStatus(status: PostEntity.Status): Flow<List<PostEntity>> =
			postDao.getPostsByStatus(status)
	
}