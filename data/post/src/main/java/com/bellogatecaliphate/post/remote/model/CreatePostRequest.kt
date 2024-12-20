package com.bellogatecaliphate.post.remote.model

data class CreatePostRequest(
	val id: String,
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val description: String
)