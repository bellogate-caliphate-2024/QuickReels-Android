package com.bellogatecaliphate.user.remote.model

import com.bellogatecaliphate.core.model.dto.User

data class UserResponse(
	val email: String,
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