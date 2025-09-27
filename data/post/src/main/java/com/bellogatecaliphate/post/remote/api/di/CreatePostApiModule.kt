package com.bellogatecaliphate.post.remote.api.di

import com.bellogatecaliphate.post.remote.api.CreatePostApi
import com.bellogatecaliphate.post.remote.model.CreatePostResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.RequestBody
import retrofit2.Retrofit
import javax.inject.Inject

internal class CreatePostImpl @Inject constructor(private val retrofit: Retrofit) : CreatePostApi {
	
	override suspend fun uploadPost(
		videoFile: RequestBody,
		postId: String,
		userId: String,
		time: String,
		caption: String
	): CreatePostResponse? {
		return retrofit.create(CreatePostApi::class.java)
			.uploadPost(videoFile, postId, userId, time, caption)
	}
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CreatePostApiModule {
	
	@Binds
	abstract fun bindApi(impl: CreatePostImpl): CreatePostApi
}