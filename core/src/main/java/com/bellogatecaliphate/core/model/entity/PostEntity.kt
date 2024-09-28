package com.bellogatecaliphate.core.model.entity

data class PostEntity(
	val id: String,
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val description: String,
	val status: Status = Status.Pending
) {
	enum class Status {
		Pending,
		InProgress,
		Success,
		Failed
	}
}
