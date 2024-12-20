package com.bellogatecaliphate.core.source.remote.di

import com.bellogatecaliphate.core.source.remote.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
	
	@Provides
	fun provideRetrofit(): Retrofit {
		return RetrofitBuilder.getRetrofit()
	}
	
}