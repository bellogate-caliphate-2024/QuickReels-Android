package com.bellogatecaliphate.user.remote.di

import com.bellogatecaliphate.user.remote.IUserRemoteDataSource
import com.bellogatecaliphate.user.remote.UserRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserRemoteDataSourceModule {
	
	@Binds
	abstract fun bindRemoteSource(remoteSource: UserRemoteDataSource): IUserRemoteDataSource
}