package com.bellogatecaliphate.post.util

import androidx.work.Data
import com.bellogatecaliphate.post.remote.model.CreatePostRequest

internal fun createPostRequest(inputData: Data): CreatePostRequest {
	val postId = inputData.getString("postId") ?: ""
	val videoFilePath = inputData.getString("videoFilePath") ?: ""
	val userId = inputData.getString("userId") ?: ""
	val time = inputData.getString("time") ?: ""
	val description = inputData.getString("description") ?: ""
	return CreatePostRequest(postId, videoFilePath, userId, time, description)
}