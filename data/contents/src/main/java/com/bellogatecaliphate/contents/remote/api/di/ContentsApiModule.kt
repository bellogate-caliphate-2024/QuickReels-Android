package com.bellogatecaliphate.contents.remote.api.di

import com.bellogatecaliphate.contents.remote.api.ContentsApi
import com.bellogatecaliphate.contents.remote.model.ContentsListResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject

internal class ContentsApiImpl @Inject constructor(private val retrofit: Retrofit) : ContentsApi {
	
	override suspend fun getContentsList(
		page: Int?,
		numberOfContentPerPage: Int
	): ContentsListResponse? {
		return retrofit.create(ContentsApi::class.java)
			.getContentsList(page, numberOfContentPerPage)
	}
	
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ContentsApiModule {
	
	@Binds
	abstract fun bindApi(impl: ContentsApiImpl): ContentsApi
	
}
