package com.bellogatecaliphate.user.local.di

import com.bellogatecaliphate.user.UserRepository
import com.bellogatecaliphate.user.local.IUserLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class UserLocalDataSourceModule {
	
	@Binds
	abstract fun bindUserLocalDataSource(userRepository: UserRepository): IUserLocalDataSource
	
}