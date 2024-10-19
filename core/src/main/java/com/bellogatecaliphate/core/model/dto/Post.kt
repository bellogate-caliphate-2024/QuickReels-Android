package com.bellogatecaliphate.core.model.dto

import com.bellogatecaliphate.core.common.util.generateRandomNumberWithFixedLength
import kotlinx.serialization.Serializable

@Serializable
data class Post(
	val id: String = generateRandomNumberWithFixedLength(10),
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val caption: String,
	val uploadProgressPercentage: String,
	val thumbnailBase64String: String?
)