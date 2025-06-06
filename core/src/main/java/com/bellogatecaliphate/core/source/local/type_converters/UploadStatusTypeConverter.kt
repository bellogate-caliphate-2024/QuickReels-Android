package com.bellogatecaliphate.core.source.local.type_converters

import androidx.room.TypeConverter
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.core.source.local.entity.StatusNames
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UploadStatusTypeConverter {
	
	@TypeConverter
	fun fromUploadStatusToJsonString(uploadStatus: PostEntity.UploadStatus): String {
		return Gson().toJson(uploadStatus)
	}
	
	@TypeConverter
	fun fromStringToUploadStatus(uploadStatusJsonString: String): PostEntity.UploadStatus {
		val type = object : TypeToken<PostEntity.UploadStatus>() {}.type
		val uploadStatus: PostEntity.UploadStatus = Gson().fromJson(uploadStatusJsonString, type)
		return when (uploadStatus.status) {
			StatusNames.IN_PROGRESS.name -> PostEntity.UploadStatus.InProgress(
				uploadStatus.uploadProgressPercentage
			)
			
			StatusNames.SUCCESS.name     -> PostEntity.UploadStatus.Success
			StatusNames.FAILED.name      -> PostEntity.UploadStatus.Failed
			else                         -> PostEntity.UploadStatus.Failed
		}
	}
}