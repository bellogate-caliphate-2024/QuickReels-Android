package com.bellogatecaliphate.post.local

import com.bellogatecaliphate.core.model.entity.PostEntity

internal interface IPostLocalDataSource {
	suspend fun savePost(postEntity: PostEntity)
	suspend fun deletePost(postEntity: PostEntity)
	suspend fun getPost(id: String): PostEntity
}
