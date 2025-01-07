package com.bellogatecaliphate.core.source.remote.di

import android.content.Context
import com.bellogatecaliphate.core.source.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
	
	@Provides
	fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
		return RetrofitClient.getRetrofit(baseUrl = "https://api.beta.getbux.com/core/27/")
	}
}