package com.bellogatecaliphate.core.source.local.entity

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
	val uploadStatus: UploadStatus = UploadStatus.InProgress(0),
) {
	sealed class UploadStatus(val uploadProgressPercentage: Int) {
		class InProgress(uploadProgressPercentage: Int) : UploadStatus(uploadProgressPercentage)
		data object Success : UploadStatus(0)
		data object Failed : UploadStatus(0)
	}
}
