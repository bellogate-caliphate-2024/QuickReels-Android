package com.bellogatecaliphate.core.source.local.type_converters

import androidx.room.TypeConverter
import com.bellogatecaliphate.core.source.local.entity.PostEntity
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
		return Gson().fromJson(uploadStatusJsonString, type)
	}
}