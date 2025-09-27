package com.bellogatecaliphate.post

import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.core.source.local.entity.PostEntity.UploadStatus
import com.bellogatecaliphate.post.local.IPostLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val UNIQUE_WORK_NAME_PREFIX = "upload_post_work_for-"

internal class PostRepository @Inject constructor(
	private val workManager: WorkManager,
	private val oneTimeWorkRequestBuilder: OneTimeWorkRequest.Builder,
	private val localDataSource: IPostLocalDataSource,
) : IPostRepository {
	
	override suspend fun uploadPost(
		postId: String,
		videoFilePath: String,
		userId: String,
		time: String,
		description: String,
		thumbnailBase64String: String
	) {
		savePost(postId, videoFilePath, userId, time, description, thumbnailBase64String)
		oneTimeWorkRequestBuilder
			.addTag(postId)
			.setInputData(
				workDataOf(
					"postId" to postId,
					"videoFilePath" to videoFilePath,
					"userId" to userId,
					"time" to time,
					"description" to description,
					"thumbnailBase64String" to thumbnailBase64String,
				)
			)
		oneTimeWorkRequestBuilder.setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
		workManager.enqueueUniqueWork(
			"$UNIQUE_WORK_NAME_PREFIX$postId",
			ExistingWorkPolicy.REPLACE,
			oneTimeWorkRequestBuilder.build()
		)
	}
	
	override suspend fun savePost(
		postId: String,
		videoFilePath: String,
		userId: String,
		time: String,
		description: String,
		thumbnailBase64String: String
	) {
		val post = PostEntity(
			id = postId,
			videoFilePath = videoFilePath,
			userId = userId,
			time = time,
			caption = description,
			thumbnailBase64String = thumbnailBase64String,
			uploadStatus = UploadStatus.InProgress(0)
		)
		localDataSource.savePost(post)
	}
	
	override suspend fun cancelUploadingPost(postId: String) {
		workManager.cancelUniqueWork("$UNIQUE_WORK_NAME_PREFIX$postId")
		val postEntity = localDataSource.getPostById(postId)
		if (postEntity != null) {
			localDataSource.deletePost(postEntity)
		}
	}
	
	override fun getOngoingPostsUploadStatus(exclude: UploadStatus): Flow<List<PostEntity>> {
		return localDataSource.getAllPostsExcept(exclude)
	}
	
	override suspend fun deletePost(postEntity: PostEntity) {
		localDataSource.deletePost(postEntity)
	}
	
	override suspend fun getPost(postId: String): PostEntity? {
		return localDataSource.getPostById(postId)
	}
	
	override fun getAllPosts(): Flow<List<PostEntity>> {
		return localDataSource.getAllPosts()
	}
}