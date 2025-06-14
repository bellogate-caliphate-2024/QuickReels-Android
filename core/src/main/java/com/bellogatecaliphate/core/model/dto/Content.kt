package com.bellogatecaliphate.core.model.dto

open class Content(
	val id: String,
	val videoUrl: String,
	val thumbnailUrl: String,
	val caption: String,
	val date: String,
	val numberOfViews: String,
	val numberOfLikes: String,
	val numberOfComments: String,
	val userId: String,
	val userName: String,
	val userProfilePicture: String,
	val isLiked: Boolean,
	val isAd: Boolean
)