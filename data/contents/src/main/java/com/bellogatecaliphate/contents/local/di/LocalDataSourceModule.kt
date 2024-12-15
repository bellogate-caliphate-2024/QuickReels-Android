package com.bellogatecaliphate.contents.local.di

import com.bellogatecaliphate.contents.local.ILocalDataSource
import com.bellogatecaliphate.contents.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {
	
	@Binds
	abstract fun bindLocalDataSource(localDataSource: LocalDataSource): ILocalDataSource
}