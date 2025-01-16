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
	open class UploadStatus(val status: String, val uploadProgressPercentage: Int) {
		class InProgress(uploadProgressPercentage: Int) :
				UploadStatus("InProgress", uploadProgressPercentage)
		
		data object Success : UploadStatus("Success", 0)
		data object Failed : UploadStatus("Failed", 0)
	}
}
