package com.bellogatecaliphate.core.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

internal enum class StatusNames {
	IN_PROGRESS,
	SUCCESS,
	FAILED
}

@Entity(tableName = "posts")
data class PostEntity(
	@PrimaryKey val id: String,
	val videoFilePath: String,
	val userId: String,
	val time: String,
	val caption: String,
	val thumbnailBase64String: String?,
	val uploadStatus: UploadStatus = UploadStatus.InProgress(0),
) {
	open class UploadStatus(val status: String, val uploadProgressPercentage: Int) {
		class InProgress(uploadProgressPercentage: Int) :
				UploadStatus(StatusNames.IN_PROGRESS.name, uploadProgressPercentage)
		
		data object Success : UploadStatus(StatusNames.SUCCESS.name, 0)
		data object Failed : UploadStatus(StatusNames.FAILED.name, 0)
	}
}
