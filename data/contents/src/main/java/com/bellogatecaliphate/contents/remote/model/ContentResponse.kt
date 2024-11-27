package com.bellogatecaliphate.contents.remote.model

import com.bellogatecaliphate.core.source.local.entity.ContentEntity

data class ContentResponse(
	val id: String?,
	val videoUrl: String?,
	val thumbnailUrl: String?,
	val caption: String?,
	val date: String?,
	val numberOfLikes: String?,
	val numberOfComments: String?,
	val userId: String?,
	val userName: String?,
	val userProfilePicture: String?
) {
	
	fun toEntity(): ContentEntity {
		return ContentEntity(
			id,
			videoUrl,
			thumbnailUrl,
			caption,
			date,
			numberOfLikes,
			numberOfComments,
			userId,
			userName,
			userProfilePicture
		)
	}
}