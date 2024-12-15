package com.bellogatecaliphate.contents.di

import com.bellogatecaliphate.contents.ContentsRepository
import com.bellogatecaliphate.contents.IContentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class ContentsRepositoryModule {
	
	@Binds
	abstract fun bindContentsRepository(impl: ContentsRepository): IContentsRepository
}