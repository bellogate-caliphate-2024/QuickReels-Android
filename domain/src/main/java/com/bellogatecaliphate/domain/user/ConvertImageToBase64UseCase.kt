package com.bellogatecaliphate.domain.user

import android.graphics.Bitmap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class ConvertImageToBase64UseCase @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher
) {
	
	suspend operator fun invoke(bitmap: Bitmap): String = withContext(ioDispatcher) {
		bitmapToBase64(bitmap)
	}
	
	@OptIn(ExperimentalEncodingApi::class)
	private fun bitmapToBase64(bitmap: Bitmap): String {
		val outputStream = ByteArrayOutputStream()
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
		val byteArray = outputStream.toByteArray()
		return Base64.encode(byteArray)
	}
}