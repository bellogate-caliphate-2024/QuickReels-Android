package com.bellogatecaliphate.post.remote.workmanager.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import com.bellogatecaliphate.post.R
import java.util.UUID

internal fun createForegroundInfo(
	context: Context,
	notificationId: String,
	uuid: UUID
): ForegroundInfo {
	val title = context.getString(R.string.notification_title)
	val cancel = context.getString(R.string.cancel_upload)
	// This PendingIntent can be used to cancel the worker
	val intent = WorkManager.getInstance(context).createCancelPendingIntent(uuid)
	
	// Create a Notification channel if necessary
	createNotificationChannel(context, notificationId)
	
	val notification = NotificationCompat.Builder(context, notificationId)
		.setContentTitle(title)
		.setTicker(title)
		.setContentText(context.getString(R.string.cancel_upload))
		.setSmallIcon(R.drawable.upload_icon)
		.setOngoing(true)
		// Add the cancel action to the notification which can
		// be used to cancel the worker
		.addAction(R.drawable.cancel, cancel, intent)
		.build()
	
	return ForegroundInfo(notificationId.toInt(), notification)
}

internal fun createNotificationChannel(context: Context, notificationId: String) {
	val channelName = "My Channel Name"
	val importance = NotificationManager.IMPORTANCE_DEFAULT
	val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
		NotificationChannel(notificationId, channelName, importance)
	} else {
		TODO("VERSION.SDK_INT < O")
	}
	channel.description = "This is my channel description"
	
	val notificationManager =
			context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
	notificationManager.createNotificationChannel(channel)
}