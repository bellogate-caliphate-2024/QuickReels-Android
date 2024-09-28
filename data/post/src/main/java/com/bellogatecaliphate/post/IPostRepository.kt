package com.bellogatecaliphate.post

import com.bellogatecaliphate.core.model.entity.PostEntity

internal interface IPostRepository {
	
	fun uploadPost(
		videoId: String,
		videoFilePath: String,
		userId: String,
		time: String,
		description: String
	)
	
	suspend fun deletePost(postEntity: PostEntity)
	suspend fun getPost(): PostEntity?
}