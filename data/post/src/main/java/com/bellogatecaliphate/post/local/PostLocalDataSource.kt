package com.bellogatecaliphate.post.local

import com.bellogatecaliphate.core.model.entity.PostEntity

internal class PostLocalDataSource : IPostLocalDataSource {
	
	override suspend fun savePost(postEntity: PostEntity) {
		TODO("Not yet implemented")
	}
	
	override suspend fun deletePost(postEntity: PostEntity) {
		TODO("Not yet implemented")
	}
	
	override suspend fun getPost(id: String): PostEntity {
		TODO("Not yet implemented")
	}
}