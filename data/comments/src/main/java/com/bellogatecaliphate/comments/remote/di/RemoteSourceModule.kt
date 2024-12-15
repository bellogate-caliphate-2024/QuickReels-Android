package com.bellogatecaliphate.comments.remote.di

import com.bellogatecaliphate.comments.remote.IRemoteSource
import com.bellogatecaliphate.comments.remote.RemoteSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class RemoteSourceModule {
	
	@Binds
	abstract fun bindRemoteSource(remoteSource: RemoteSource): IRemoteSource
}