package com.bellogatecaliphate.user.remote.firebase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

internal class FirebaseDataSource @Inject constructor(
	private val auth: FirebaseAuth,
) {
	
	fun getUserEmail(): String? {
		return auth.currentUser?.email
	}
	
	fun isUserLoggedIn(): Boolean {
		return auth.currentUser != null
	}
}