package com.bellogatecaliphate.post.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object WorkManagerModule {
	
	@Provides
	fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
		return WorkManager.getInstance(context)
	}
}