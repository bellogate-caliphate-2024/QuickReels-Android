package com.bellogatecaliphate.user.remote.firebase.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseDataSourceModule {
	
	@Provides
	fun provideFirebaseAuth(): FirebaseAuth {
		return FirebaseAuth.getInstance()
	}
}