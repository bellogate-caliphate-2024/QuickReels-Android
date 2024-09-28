package com.bellogatecaliphate.core.model.entity

data class PostEntity(
	val id: String,
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val description: String,
	val status: STATUS = STATUS.PENDING
) {
	enum class STATUS {
		PENDING,
		IN_PROGRESS,
		SUCCESS,
		FAILED
	}
}
