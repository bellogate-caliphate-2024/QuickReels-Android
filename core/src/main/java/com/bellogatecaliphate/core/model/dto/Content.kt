package com.bellogatecaliphate.core.model.dto

data class Content(
	val id: String,
	val videoUrl: String,
	val thumbnailUrl: String,
	val caption: String,
	val date: String,
	val numberOfLikes: String,
	val numberOfComments: String,
	val userId: String,
	val userName: String,
	val userProfilePicture: String
)