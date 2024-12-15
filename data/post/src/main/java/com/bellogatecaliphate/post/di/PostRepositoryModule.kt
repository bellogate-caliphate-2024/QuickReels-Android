package com.bellogatecaliphate.post.di

import com.bellogatecaliphate.post.IPostRepository
import com.bellogatecaliphate.post.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class PostRepositoryModule {
	
	@Binds
	abstract fun bindPostRepository(impl: PostRepository): IPostRepository
}