package com.bellogatecaliphate.post.remote

import com.bellogatecaliphate.post.remote.model.CreatePostRequest

internal interface IPostRemoteDataSource {
	
	suspend fun uploadPost(post: CreatePostRequest)
}