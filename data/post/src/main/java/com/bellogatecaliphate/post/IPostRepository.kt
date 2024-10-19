package com.bellogatecaliphate.post

import com.bellogatecaliphate.core.source.database.entity.PostEntity

interface IPostRepository {
	
	fun uploadPost(
		videoId: String,
		videoFilePath: String,
		userId: String,
		time: String,
		description: String,
		uploadProgressPercentage: String,
		thumbnailBase64String: String
	)
	
	suspend fun deletePost(postEntity: PostEntity)
	suspend fun getPost(): PostEntity?
}