package com.bellogatecaliphate.post

import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import javax.inject.Inject

internal class PostRepository @Inject constructor(
	private val workManager: WorkManager,
	private val workRequestBuilder: OneTimeWorkRequest.Builder
) : IPostRepository {
	
	override fun uploadPost(
		videoId: String,
		videoFilePath: String,
		userId: String,
		time: String,
		description: String,
		uploadProgressPercentage: String,
		thumbnailBase64String: String
	) {
		workRequestBuilder
			.addTag(videoId)
			.setInputData(
				workDataOf(
					"videoId" to videoId,
					"videoFilePath" to videoFilePath,
					"userId" to userId,
					"time" to time,
					"description" to description,
					"uploadProgressPercentage" to "",
					"thumbnailBase64String" to "",
				)
			)
		workRequestBuilder.setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
		workManager.enqueue(workRequestBuilder.build())
	}
	
	override suspend fun deletePost(postEntity: PostEntity) {
	}
	
	override suspend fun getPost(): PostEntity? {
		return null
	}
}