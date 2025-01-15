package com.bellogatecaliphate.post.remote.api.progress_request_body

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream
import java.io.IOException

private const val DEFAULT_BUFFER_SIZE = 2048

internal class ProgressRequestBody(
	private val file: File,
	private val contentType: String,
	private val listener: UploadCallbacks
) : RequestBody() {
	
	interface UploadCallbacks {
		suspend fun onProgressUpdate(percentage: Int)
		suspend fun onError()
		suspend fun onFinish()
	}
	
	private val job = Job()
	private val coroutineScope = CoroutineScope(job + Dispatchers.IO)
	
	override fun contentType(): MediaType? {
		return contentType.toMediaTypeOrNull()
	}
	
	override fun contentLength(): Long {
		return file.length()
	}
	
	override fun writeTo(sink: BufferedSink) {
		val fileLength = file.length()
		val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
		val inputStream = FileInputStream(file)
		var uploaded = 0L
		
		try {
			var read: Int
			while (inputStream.read(buffer).also { read = it } != - 1) {
				uploaded += read
				sink.write(buffer, 0, read)
				val progress = (100 * uploaded / fileLength).toInt()
				coroutineScope.launch {
					listener.onProgressUpdate(progress)
				}
			}
			coroutineScope.launch {
				listener.onFinish()
			}
		}
		catch (e: IOException) {
			coroutineScope.launch {
				listener.onError()
			}
		}
		finally {
			inputStream.close()
			job.cancel()
		}
	}
}