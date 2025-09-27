package com.bellogatecaliphate.post

import com.bellogatecaliphate.core.source.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

interface IPostRepository {
	
	suspend fun uploadPost(
		postId: String,
		videoFilePath: String,
		userId: String,
		time: String,
		description: String,
		thumbnailBase64String: String
	)
	
	suspend fun savePost(
		postId: String,
		videoFilePath: String,
		userId: String,
		time: String,
		description: String,
		thumbnailBase64String: String
	)
	
	suspend fun cancelUploadingPost(postId: String)
	fun getOngoingPostsUploadStatus(exclude: PostEntity.UploadStatus): Flow<List<PostEntity>>
	suspend fun deletePost(postEntity: PostEntity)
	suspend fun getPost(postId: String): PostEntity?
	fun getAllPosts(): Flow<List<PostEntity>>
}