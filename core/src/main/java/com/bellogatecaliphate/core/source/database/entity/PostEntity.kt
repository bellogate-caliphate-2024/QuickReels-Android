package com.bellogatecaliphate.core.source.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
	@PrimaryKey val id: String,
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
