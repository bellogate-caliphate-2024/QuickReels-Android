package com.bellogatecaliphate.user.remote.model

data class UserResponse(
	val email: String,
	val accountName: String,
	val profilePictureUrl: String,
	val numberOfLikes: String,
	val numberOfViews: String
)