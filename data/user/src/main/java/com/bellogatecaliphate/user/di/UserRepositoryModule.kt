package com.bellogatecaliphate.user.di

import com.bellogatecaliphate.user.IUserRepository
import com.bellogatecaliphate.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserRepositoryModule {
	
	@Binds
	abstract fun bindUserRepository(userRepository: UserRepository): IUserRepository
	
}