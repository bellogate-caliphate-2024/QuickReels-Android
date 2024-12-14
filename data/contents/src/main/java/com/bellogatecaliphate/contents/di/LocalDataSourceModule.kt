package com.bellogatecaliphate.contents.di

import com.bellogatecaliphate.contents.local.ILocalDataSource
import com.bellogatecaliphate.contents.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class LocalDataSourceModule {
	
	@Binds
	abstract fun bindLocalDataSource(localDataSource: LocalDataSource): ILocalDataSource
}