package com.bellogatecaliphate.core.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contents")
data class ContentEntity(
	@PrimaryKey
	val id: String,
	val videoUrl: String?,
	val thumbnailUrl: String?,
	val caption: String?,
	val date: String?,
	val numberOfViews: String?,
	val numberOfLikes: String?,
	val numberOfComments: String?,
	val userId: String?,
	val userName: String?,
	val userProfilePicture: String?
)