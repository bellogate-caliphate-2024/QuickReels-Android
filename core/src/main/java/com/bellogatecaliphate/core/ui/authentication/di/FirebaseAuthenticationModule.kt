package com.bellogatecaliphate.core.ui.authentication.di

import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseAuthenticationModule {
	
	@Provides
	fun provideGoogleIdToken(): GoogleIdTokenCredential.Companion {
		return GoogleIdTokenCredential.Companion
	}
	
	@Provides
	fun provideGetGoogleIdOptionBuilder(): GetGoogleIdOption.Builder {
		return GetGoogleIdOption.Builder()
	}
	
	@Provides
	fun provideGetCredentialRequestBuilder(): GetCredentialRequest.Builder {
		return GetCredentialRequest.Builder()
	}
}