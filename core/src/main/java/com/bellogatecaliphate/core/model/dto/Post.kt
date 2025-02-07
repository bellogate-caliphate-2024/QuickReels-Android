package com.bellogatecaliphate.core.model.dto

import com.bellogatecaliphate.core.util.generateRandomNumberWithFixedLength
import kotlinx.serialization.Serializable

@Serializable
data class Post(
	val id: String = generateRandomNumberWithFixedLength(10),
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val caption: String,
	val thumbnailBase64String: String?,
	val uploadProgressPercentage: String,
	val isUploading: Boolean = false,
	val isUploaded: Boolean = false,
	val isUploadFailed: Boolean = false
)