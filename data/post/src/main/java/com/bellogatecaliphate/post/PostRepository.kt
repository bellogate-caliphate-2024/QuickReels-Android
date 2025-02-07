package com.bellogatecaliphate.post

import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.post.local.IPostLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class PostRepository @Inject constructor(
	private val workManager: WorkManager,
	private val oneTimeWorkRequestBuilder: OneTimeWorkRequest.Builder,
	private val localDataSource: IPostLocalDataSource,
) : IPostRepository {
	
	override fun uploadPost(
		videoId: String,
		videoFilePath: String,
		userId: String,
		time: String,
		description: String,
		thumbnailBase64String: String
	) {
		oneTimeWorkRequestBuilder
			.addTag(videoId)
			.setInputData(
				workDataOf(
					"videoId" to videoId,
					"videoFilePath" to videoFilePath,
					"userId" to userId,
					"time" to time,
					"description" to description,
					"thumbnailBase64String" to thumbnailBase64String,
				)
			)
		oneTimeWorkRequestBuilder.setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
		workManager.enqueueUniqueWork(
			"uploadVideoWorkFor-$videoId",
			ExistingWorkPolicy.REPLACE,
			oneTimeWorkRequestBuilder.build()
		)
	}
	
	override suspend fun getOngoingPostsUploadStatus(exclude: PostEntity.UploadStatus): Flow<List<PostEntity>> {
		return localDataSource.getAllPostsExcept(exclude)
	}
	
	override suspend fun deletePost(postEntity: PostEntity) {
	}
	
	override suspend fun getPost(): PostEntity? {
		return null
	}
}