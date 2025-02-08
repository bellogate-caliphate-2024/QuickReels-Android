package com.bellogatecaliphate.domain.post

import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.util.Size
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class GetVideoThumbnailUseCase @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher
) {
	
	suspend operator fun invoke(videoPath: String): Bitmap = withContext(ioDispatcher) {
		val videFile = File(videoPath)
		val bitmap = ThumbnailUtils.createVideoThumbnail(
			videFile,
			Size(50, 50),
			null
		)
		bitmap
	}
}