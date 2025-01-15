package com.bellogatecaliphate.post.remote

import com.bellogatecaliphate.post.remote.model.CreatePostRequest
import com.bellogatecaliphate.post.remote.model.CreatePostResponse

internal interface IPostRemoteDataSource {
	
	suspend fun uploadPost(post: CreatePostRequest): CreatePostResponse?
}