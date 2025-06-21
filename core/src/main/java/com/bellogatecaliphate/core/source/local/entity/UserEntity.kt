package com.bellogatecaliphate.core.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bellogatecaliphate.core.model.dto.User

@Entity(tableName = "user")
data class UserEntity(
	@PrimaryKey val email: String,
	val accountName: String,
	val profilePictureUrl: String,
	val numberOfLikes: String,
	val numberOfViews: String,
	val numberOfFollowers: String,
	val numberOfFollowing: String
) {
	
	fun toUser(): User {
		return User(
			email,
			accountName,
			profilePictureUrl,
			numberOfLikes,
			numberOfViews,
			numberOfFollowers,
			numberOfFollowing
		)
	}
}