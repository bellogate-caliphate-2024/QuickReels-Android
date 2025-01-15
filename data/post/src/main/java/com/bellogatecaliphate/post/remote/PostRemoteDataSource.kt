package com.bellogatecaliphate.post.remote

import com.bellogatecaliphate.post.remote.api.CreatePostApi
import com.bellogatecaliphate.post.remote.model.CreatePostRequest
import com.bellogatecaliphate.post.remote.model.CreatePostResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

internal class PostRemoteDataSource @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val api: CreatePostApi,
) : IPostRemoteDataSource {
	
	override suspend fun uploadPost(post: CreatePostRequest): CreatePostResponse? =
			withContext(ioDispatcher) {
				val videoFile = File(post.videoFilePath)
				val videoFilePart = createMultipartBodyPart(videoFile, videoFile.name)
				try {
					val result = api.uploadPost(
						videoFilePart,
						post.id,
						post.userId,
						post.time,
						post.description
					)
					return@withContext result
				}
				catch (e: Exception) {
					return@withContext null
				}
			}
	
	private fun createMultipartBodyPart(file: File, partName: String): MultipartBody.Part {
		val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
		return MultipartBody.Part.createFormData(partName, file.name, requestFile)
	}
	
}