package com.bellogatecaliphate.post.remote

import com.bellogatecaliphate.post.remote.api.CreatePostApi
import com.bellogatecaliphate.post.remote.api.progress_request_body.ProgressRequestBody
import com.bellogatecaliphate.post.remote.model.CreatePostRequest
import com.bellogatecaliphate.post.remote.model.CreatePostResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

internal class PostRemoteDataSource @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val api: CreatePostApi,
) : IPostRemoteDataSource {
	
	override suspend fun uploadPost(
		post: CreatePostRequest,
		onProgressUpdate: suspend (Int) -> Unit,
		onError: suspend () -> Unit,
		onFinish: suspend () -> Unit
	): CreatePostResponse? =
			withContext(ioDispatcher) {
				val videoFile = File(post.videoFilePath)
				val progressRequestBody = ProgressRequestBody(
					file = videoFile,
					contentType = "multipart/form-data",
					listener = object : ProgressRequestBody.UploadCallbacks {
						
						override suspend fun onProgressUpdate(percentage: Int) {
							// Update your UI with the progress percentage %
							onProgressUpdate(percentage)
						}
						
						override suspend fun onError() {
							onError()
						}
						
						override suspend fun onFinish() {
							onFinish()
						}
					}
				)
				try {
					val result = api.uploadPost(
						progressRequestBody,
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
	
}