package com.bellogatecaliphate.core.model.entity

data class PostEntity(
	val id: String,
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val caption: String,
	val uploadProgressPercentage: String,
	val thumbnailBase64String: String?,
	val status: Status = Status.InProgress,
) {
	enum class Status {
		InProgress,
		Success,
		Failed
	}
}
