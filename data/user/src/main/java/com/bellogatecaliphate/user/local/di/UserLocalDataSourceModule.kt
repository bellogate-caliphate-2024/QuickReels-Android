package com.bellogatecaliphate.user.local.di

import com.bellogatecaliphate.user.local.IUserLocalDataSource
import com.bellogatecaliphate.user.local.UserLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserLocalDataSourceModule {
	
	@Binds
	abstract fun bindUserLocalDataSource(userLocalDataSource: UserLocalDataSource): IUserLocalDataSource
	
}