package com.bellogatecaliphate.post.local.di

import com.bellogatecaliphate.post.local.IPostLocalDataSource
import com.bellogatecaliphate.post.local.PostLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PostLocalDataSourceModule {
	
	@Binds
	abstract fun bindPostLocalDataSource(postLocalDataSource: PostLocalDataSource): IPostLocalDataSource
	
}