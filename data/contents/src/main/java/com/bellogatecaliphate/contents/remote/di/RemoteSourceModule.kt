package com.bellogatecaliphate.contents.remote.di

import contents.remote.RemoteSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteSourceModule {
	
	@Binds
	abstract fun bindRemoteSource(remoteSource: RemoteSource): IRemoteSource
}