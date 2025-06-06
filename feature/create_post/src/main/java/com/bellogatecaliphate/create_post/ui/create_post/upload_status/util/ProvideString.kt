package com.bellogatecaliphate.create_post.ui.create_post.upload_status.util

import android.content.Context
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.R

fun provideUploadStatusMessage(context: Context, post: Post): String {
	return when {
		post.isUploading    -> context.getString(R.string.uploading, post.uploadProgressPercentage)
		post.isUploaded     -> context.getString(R.string.uploaded_successfully)
		post.isUploadFailed -> context.getString(R.string.upload_failed)
		else                -> context.getString(R.string.upload_failed)
	}
}