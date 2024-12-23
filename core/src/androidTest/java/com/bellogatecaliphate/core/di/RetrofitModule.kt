package com.bellogatecaliphate.core.di

import android.content.Context
import com.bellogatecaliphate.core.source.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
@TestInstallIn(
	components = [SingletonComponent::class],
	replaces = [RetrofitModule::class]
)
object RetrofitModule {
	
	@Provides
	fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
		return RetrofitClient.getRetrofit(context, baseUrl = "http://localhost:8080")
	}
}