package com.bellogatecaliphate.core.source.remote.di

import android.content.Context
import com.bellogatecaliphate.core.source.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
	
	@Provides
	fun provideRetrofit(@ActivityContext context: Context): Retrofit {
		return RetrofitClient.getRetrofit(context)
	}
}