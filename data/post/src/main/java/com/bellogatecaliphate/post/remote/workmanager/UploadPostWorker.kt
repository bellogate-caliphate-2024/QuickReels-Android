package com.bellogatecaliphate.post.remote.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.post.R
import com.bellogatecaliphate.post.local.IPostLocalDataSource
import com.bellogatecaliphate.post.remote.IPostRemoteDataSource
import com.bellogatecaliphate.post.util.createPostEntity
import com.bellogatecaliphate.post.util.createPostRequest

private const val DEFAULT_NOTIFICATION_ID = "00000000"

internal class UploadPostWorker(
	private val localDataSource: IPostLocalDataSource,
	private val remoteDataSource: IPostRemoteDataSource,
	context: Context,
	params: WorkerParameters
) : CoroutineWorker(context, params) {
	
	override suspend fun getForegroundInfo(): ForegroundInfo {
		return createForegroundInfo(inputData.getString("videoId") ?: DEFAULT_NOTIFICATION_ID)
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
	
	private fun createForegroundInfo(notificationId: String): ForegroundInfo {
		val title = applicationContext.getString(R.string.notification_title)
		val cancel = applicationContext.getString(R.string.cancel_upload)
		// This PendingIntent can be used to cancel the worker
		val intent = WorkManager.getInstance(applicationContext).createCancelPendingIntent(id)
		
		// Create a Notification channel if necessary
		createNotificationChannel(applicationContext, notificationId)
		
		val notification = NotificationCompat.Builder(applicationContext, notificationId)
			.setContentTitle(title)
			.setTicker(title)
			.setContentText(applicationContext.getString(R.string.cancel_upload))
			.setSmallIcon(R.drawable.upload_icon)
			.setOngoing(true)
			// Add the cancel action to the notification which can
			// be used to cancel the worker
			.addAction(R.drawable.cancel, cancel, intent)
			.build()
		
		return ForegroundInfo(notificationId.toInt(), notification)
	}
	
	private fun createNotificationChannel(context: Context, notificationId: String) {
		val channelName = "My Channel Name"
		val importance = NotificationManager.IMPORTANCE_DEFAULT
		val channel = NotificationChannel(notificationId, channelName, importance)
		channel.description = "This is my channel description"
		
		val notificationManager =
				context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
		notificationManager.createNotificationChannel(channel)
	}
}
