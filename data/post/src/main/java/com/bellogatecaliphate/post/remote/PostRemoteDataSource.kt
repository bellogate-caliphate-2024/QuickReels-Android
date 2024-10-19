package com.bellogatecaliphate.post.remote

import com.bellogatecaliphate.post.remote.model.CreatePostRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class PostRemoteDataSource @Inject constructor(
	private val ioDispatcher: CoroutineContext = Dispatchers.IO
) : IPostRemoteDataSource {
	
	override suspend fun uploadPost(post: CreatePostRequest) = withContext(ioDispatcher) {
	
	}
}