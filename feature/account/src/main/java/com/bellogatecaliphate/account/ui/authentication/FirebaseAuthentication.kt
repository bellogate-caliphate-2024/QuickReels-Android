package com.bellogatecaliphate.account.ui.authentication

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.ClearCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthentication @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val auth: FirebaseAuth,
	private val idOptionBuilder: GetGoogleIdOption.Builder,
	private val credentialRequestBuilder: GetCredentialRequest.Builder,
	private val googleIdTokenCredential: GoogleIdTokenCredential.Companion
) {
	
	// We are passing in context instead on injecting using hilt it because firebase auth needs an activity
	// related context to perform the login not application context
	suspend fun performLogin(context: Context, serverClientId: String): Boolean {
		val credential =
				getCredentialResponse(context, getCredentialRequest(serverClientId)).credential
		
		return if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
			val googleIdTokenCredential = googleIdTokenCredential.createFrom(credential.data)
			firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
		} else {
			false
		}
	}
	
	suspend fun logoutUser(context: Context): Boolean = withContext(ioDispatcher) {
		try {
			auth.signOut()
			val clearRequest = ClearCredentialStateRequest()
			getCredentialManager(context).clearCredentialState(clearRequest)
			true
		}
		catch (e: ClearCredentialException) {
			false
		}
	}
	
	private suspend fun firebaseAuthWithGoogle(idToken: String): Boolean =
			suspendCoroutine { continuation ->
				val authCredential = GoogleAuthProvider.getCredential(idToken, null)
				auth.signInWithCredential(authCredential).addOnCompleteListener { task ->
					continuation.resumeWith(Result.success(task.isSuccessful))
				}
			}
	
	private fun getCredentialRequest(serverClientId: String): GetCredentialRequest {
		val idOption = idOptionBuilder
			// Your server's client ID, not your Android client ID.
			.setServerClientId(serverClientId)
			// Only show accounts previously used to sign in.
			.setFilterByAuthorizedAccounts(false)
			.build()
		
		// Create the Credential Manager request
		return credentialRequestBuilder
			.addCredentialOption(idOption)
			.build()
	}
	
	private suspend fun getCredentialResponse(
		context: Context,
		request: GetCredentialRequest
	): GetCredentialResponse {
		return getCredentialManager(context).getCredential(
			request = request,
			context = context
		)
	}
	
	private fun getCredentialManager(context: Context): CredentialManager {
		return CredentialManager.create(context)
	}
}