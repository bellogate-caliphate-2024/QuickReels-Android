package com.bellogatecaliphate.domain.post

import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Base64
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class GetVideoThumbnailUseCase @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher
) {
	
	suspend operator fun invoke(videoPath: String): String? = withContext(ioDispatcher) {
		val bitmap = ThumbnailUtils.createVideoThumbnail(
			videoPath,
			MediaStore.Video.Thumbnails.MINI_KIND
		) ?: return@withContext null
		
		val byteArrayOutputStream = ByteArrayOutputStream()
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
		val byteArray = byteArrayOutputStream.toByteArray()
		
		Base64.encodeToString(byteArray, Base64.DEFAULT)
	}
}