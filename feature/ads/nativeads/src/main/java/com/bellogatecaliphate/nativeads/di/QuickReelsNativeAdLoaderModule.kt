package com.bellogatecaliphate.nativeads.di

import android.content.Context
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object QuickReelsNativeAdLoaderModule {
	
	@Provides
	fun provideAdLoaderBuilder(@ApplicationContext context: Context): AdLoader.Builder {
		return AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
	}
	
	@Provides
	fun provideAdRequest() = AdRequest.Builder().build()
}