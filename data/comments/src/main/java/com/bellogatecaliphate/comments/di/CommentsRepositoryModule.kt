package com.bellogatecaliphate.comments.di

import com.bellogatecaliphate.comments.CommentsRepository
import com.bellogatecaliphate.comments.ICommentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class CommentsRepositoryModule {
	
	@Binds
	abstract fun bindCommentsRepository(impl: CommentsRepository): ICommentsRepository
}