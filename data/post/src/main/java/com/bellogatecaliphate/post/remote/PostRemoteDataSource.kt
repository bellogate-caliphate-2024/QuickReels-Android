package com.bellogatecaliphate.post.remote

import com.bellogatecaliphate.post.remote.model.CreatePostRequest
import kotlinx.coroutines.withContext

internal class PostRemoteDataSource : IPostRemoteDataSource {
	
	override suspend fun uploadPost(post: CreatePostRequest) = withContext() { {
	
	}
}