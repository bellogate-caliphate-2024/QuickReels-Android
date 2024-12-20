package com.bellogatecaliphate.core.model.dto

data class User(
	val id: String,
	val email: String,
	val accountName: String,
	val profilePictureUrl: String,
	val numberOfLikes: String,
	val numberOfViews: String
)