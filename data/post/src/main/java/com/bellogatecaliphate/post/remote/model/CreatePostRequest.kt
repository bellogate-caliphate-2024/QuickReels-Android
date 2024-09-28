package com.bellogatecaliphate.post.remote.model

import com.bellogatecaliphate.core.model.entity.PostEntity

data class CreatePostRequest(
	val id: String,
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val description: String
) {
	fun toPostEntity(): PostEntity {
		return PostEntity(
			id, videoFilePath, userId, time, description
		)
	}
}