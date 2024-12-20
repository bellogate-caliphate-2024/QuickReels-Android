package com.bellogatecaliphate.post.remote

import com.bellogatecaliphate.post.remote.model.CreatePostRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PostRemoteDataSource @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher
) : IPostRemoteDataSource {
	
	override suspend fun uploadPost(post: CreatePostRequest) = withContext(ioDispatcher) {
	
	}
}