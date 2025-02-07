package com.bellogatecaliphate.post.util

import androidx.work.Data
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.core.source.local.entity.PostEntity.UploadStatus
import com.bellogatecaliphate.post.remote.model.CreatePostRequest

internal fun createPostEntity(inputData: Data): PostEntity {
	val videoId = inputData.getString("videoId") ?: ""
	val videoFilePath = inputData.getString("videoFilePath") ?: ""
	val userId = inputData.getString("userId") ?: ""
	val time = inputData.getString("time") ?: ""
	val description = inputData.getString("description") ?: ""
	val thumbnailBase64String = inputData.getString("thumbnailBase64String") ?: ""
	val status: UploadStatus = UploadStatus.InProgress(0)
	return PostEntity(
		videoId,
		videoFilePath,
		userId,
		time,
		description,
		thumbnailBase64String,
		status
	)
}

internal fun createPostRequest(inputData: Data): CreatePostRequest {
	val videoId = inputData.getString("videoId") ?: ""
	val videoFilePath = inputData.getString("videoFilePath") ?: ""
	val userId = inputData.getString("userId") ?: ""
	val time = inputData.getString("time") ?: ""
	val description = inputData.getString("description") ?: ""
	return CreatePostRequest(videoId, videoFilePath, userId, time, description)
}