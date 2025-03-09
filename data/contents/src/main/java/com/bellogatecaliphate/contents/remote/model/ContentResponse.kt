package com.bellogatecaliphate.contents.remote.model

data class ContentResponse(
	val id: String? = null,
	val videoUrl: String? = null,
	val thumbnailUrl: String? = null,
	val caption: String? = null,
	val date: String? = null,
	val numberOfViews: String? = null,
	val numberOfLikes: String? = null,
	val numberOfComments: String? = null,
	val userId: String? = null,
	val userName: String? = null,
	val userProfilePicture: String? = null,
	val isLiked: Boolean? = null,
	val isAd: Boolean? = null
)