package com.bellogatecaliphate.post.local

import com.bellogatecaliphate.core.source.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

internal interface IPostLocalDataSource {
	suspend fun savePost(postEntity: PostEntity)
	suspend fun deletePost(postEntity: PostEntity)
	suspend fun getPostById(postId: String): PostEntity?
	fun getPostsByStatus(status: PostEntity.Status): Flow<List<PostEntity>>
}
