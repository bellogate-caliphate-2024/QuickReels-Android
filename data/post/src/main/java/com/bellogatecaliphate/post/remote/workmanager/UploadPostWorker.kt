package com.bellogatecaliphate.post.remote.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.post.local.IPostLocalDataSource
import com.bellogatecaliphate.post.remote.IPostRemoteDataSource
import com.bellogatecaliphate.post.remote.workmanager.notification.createForegroundInfo
import com.bellogatecaliphate.post.util.createPostEntity
import com.bellogatecaliphate.post.util.createPostRequest
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

private const val DEFAULT_NOTIFICATION_ID = "00000000"

/**
 * If you want to know how I setup my work manager without any errors, read the documentation and
 * also watch this video https://www.youtube.com/watch?v=O9_RSYSmeIE , because there is a configuration
 * you need to add to your app module manifest file that is note in the documentation.
 **/
@HiltWorker
internal class UploadPostWorker @AssistedInject constructor(
	@Assisted context: Context,
	@Assisted params: WorkerParameters,
	private val localDataSource: IPostLocalDataSource,
	private val remoteDataSource: IPostRemoteDataSource
) : CoroutineWorker(context, params) {
	
	override suspend fun getForegroundInfo(): ForegroundInfo {
		return createForegroundInfo(
			applicationContext,
			inputData.getString("videoId") ?: DEFAULT_NOTIFICATION_ID,
			id
		)
	}
	
	override suspend fun doWork(): Result {
		savePost(inputData)
		return syncPost(inputData)
	}
	
	private suspend fun savePost(inputData: Data) {
		localDataSource.savePost(createPostEntity(inputData))
	}
	
	private suspend fun syncPost(inputData: Data): Result {
		val request = createPostRequest(inputData)
		
		return try {
			remoteDataSource.uploadPost(request)
			updatePostStatus(request.id, PostEntity.Status.Success)
			Result.success()
		}
		catch (e: Exception) {
			updatePostStatus(request.id, PostEntity.Status.Failed)
			Result.failure()
		}
	}
	
	private suspend fun updatePostStatus(postId: String, status: PostEntity.Status) {
		val savedPost =
				localDataSource.getPostById(postId)?.copy(status = status)
		savedPost?.let { localDataSource.savePost(it) }
	}
	
}
