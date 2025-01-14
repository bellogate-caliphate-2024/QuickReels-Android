package com.bellogatecaliphate.post.di

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import com.bellogatecaliphate.post.remote.workmanager.UploadPostWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OneTimeWorkRequestBuilderModule {
	
	@Provides
	fun provideOneTimeWorkRequestBuilder(): OneTimeWorkRequest.Builder {
		val constraints = Constraints.Builder()
			.setRequiredNetworkType(NetworkType.CONNECTED)
			.build()
		
		return OneTimeWorkRequest.Builder(UploadPostWorker::class.java)
			.setConstraints(constraints)
	}
}