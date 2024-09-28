package com.bellogatecaliphate.post.remote.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.bellogatecaliphate.post.R
import com.bellogatecaliphate.post.local.IPostLocalDataSource
import com.bellogatecaliphate.post.remote.IPostRemoteDataSource
import com.bellogatecaliphate.post.remote.model.CreatePostRequest

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
		val result = savePost(inputData)
		return if (result) {
			syncPost(inputData)
		} else {
			Result.failure()
		}
	}
	
	private suspend fun savePost(inputData: Data): Boolean {
		val post = createPostRequest(inputData)
		if (post != null) {
			localDataSource.savePost(post.toPostEntity())
			return true
		} else return false
	}
	
	private suspend fun syncPost(inputData: Data): Result {
		val post = createPostRequest(inputData) ?: return Result.failure()
		val result = remoteDataSource.uploadPost(post)
		return if (result == null) Result.success() else Result.failure()
	}
	
	private fun createPostRequest(inputData: Data): CreatePostRequest? {
		val videoId = inputData.getString("videoId")
		val videoFilePath = inputData.getString("videoFilePath")
		val userId = inputData.getString("userId")
		val time = inputData.getString("time")
		val description = inputData.getString("description")
		
		if (videoId != null &&
		    videoFilePath != null &&
		    userId != null &&
		    time != null &&
		    description != null
		) {
			return CreatePostRequest(videoId, videoFilePath, userId, time, description)
		}
		return null
	}
	
	private fun createForegroundInfo(notificationId: String): ForegroundInfo {
		val title = applicationContext.getString(R.string.notification_title)
		val cancel = applicationContext.getString(R.string.cancel_upload)
		// This PendingIntent can be used to cancel the worker
		val intent = WorkManager.getInstance(applicationContext).createCancelPendingIntent(id)
		
		// Create a Notification channel if necessary
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			createNotificationChannel(applicationContext, notificationId)
		}
		
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
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val channelName = "My Channel Name"
			val importance = NotificationManager.IMPORTANCE_DEFAULT
			val channel = NotificationChannel(notificationId, channelName, importance)
			channel.description = "This is my channel description"
			
			val notificationManager =
					context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
			notificationManager.createNotificationChannel(channel)
		}
	}
}
