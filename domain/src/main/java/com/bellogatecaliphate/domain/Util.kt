package com.bellogatecaliphate.domain

import com.bellogatecaliphate.contents.remote.model.ContentResponse
import com.bellogatecaliphate.core.model.dto.Content

fun ContentResponse.toContent(): Content {
	return Content(
		id ?: "",
		videoUrl ?: "",
		thumbnailUrl ?: "",
		caption ?: "",
		date ?: "",
		numberOfViews ?: "",
		numberOfLikes ?: "",
		numberOfComments ?: "",
		userId ?: "",
		userName ?: "",
		userProfilePicture ?: "",
		isLiked ?: false,
		isAd ?: false
	)
}