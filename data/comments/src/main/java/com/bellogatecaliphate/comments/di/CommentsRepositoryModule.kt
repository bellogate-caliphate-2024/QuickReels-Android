package com.bellogatecaliphate.comments.di

import com.bellogatecaliphate.comments.CommentsRepository
import com.bellogatecaliphate.comments.ICommentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CommentsRepositoryModule {
	
	@Binds
	abstract fun bindCommentsRepository(impl: CommentsRepository): ICommentsRepository
}