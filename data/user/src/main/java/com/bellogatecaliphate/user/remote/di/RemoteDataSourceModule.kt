package com.bellogatecaliphate.user.remote.di

import com.bellogatecaliphate.user.remote.IRemoteDataSource
import com.bellogatecaliphate.user.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {
	
	@Binds
	abstract fun bindRemoteSource(remoteSource: RemoteDataSource): IRemoteDataSource
}