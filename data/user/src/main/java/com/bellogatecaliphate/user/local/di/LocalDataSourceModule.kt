package com.bellogatecaliphate.user.local.di

import com.bellogatecaliphate.user.local.ILocalDataSource
import com.bellogatecaliphate.user.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {
	
	@Binds
	abstract fun bindUserLocalDataSource(userLocalDataSource: LocalDataSource): ILocalDataSource
	
}