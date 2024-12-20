package com.bellogatecaliphate.post.remote.di

import com.bellogatecaliphate.post.remote.IPostRemoteDataSource
import com.bellogatecaliphate.post.remote.PostRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PostRemoteDataSourceModule {
	
	@Binds
	abstract fun bindPostRemoteDataSource(impl: PostRemoteDataSource): IPostRemoteDataSource
}