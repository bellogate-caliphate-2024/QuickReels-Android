package com.bellogatecaliphate.domain.post

import android.content.Context
import android.graphics.Bitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class SaveVideoThumbnailFileUseCase @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	@ApplicationContext private val context: Context
) {
	
	suspend operator fun invoke(thumbnail: Bitmap): String = withContext(ioDispatcher) {
		val thumbnailFile =
				bitmapToFile(thumbnail, context, "${System.currentTimeMillis()}-thumbnail.png")
		thumbnailFile?.absolutePath ?: ""
	}
	
	private fun bitmapToFile(bitmap: Bitmap, context: Context, fileName: String): File? {
		val file = File(context.cacheDir, fileName)
		return try {
			val fileOutputStream = FileOutputStream(file)
			bitmap.compress(
				Bitmap.CompressFormat.PNG,
				100,
				fileOutputStream
			)
			fileOutputStream.flush()
			fileOutputStream.close()
			file
		}
		catch (e: IOException) {
			e.printStackTrace()
			null
		}
	}
}