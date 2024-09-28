package com.bellogatecaliphate.core.model.dto

import com.bellogatecaliphate.core.model.entity.PostEntity
import com.bellogatecaliphate.core.model.util.generateRandomNumberWithFixedLength
import kotlinx.serialization.Serializable

@Serializable
data class Post(
	val id: String = generateRandomNumberWithFixedLength(10),
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val description: String
) {
	
	fun toPostEntity(): PostEntity {
		return PostEntity(id, videoFilePath, userId, time, description)
	}
}