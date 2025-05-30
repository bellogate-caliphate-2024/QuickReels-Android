package com.bellogatecaliphate.user.remote

import com.bellogatecaliphate.user.remote.api.UserApi
import com.bellogatecaliphate.user.remote.firebase.FirebaseDataSource
import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteDataSource @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val firebaseDataSource: FirebaseDataSource,
	private val api: UserApi
) : IRemoteDataSource {
	
	override suspend fun getUsers(page: Int, numberOfUsersPerPage: Int): UsersResponse? =
			withContext(ioDispatcher) {
				api.getUsers(page, numberOfUsersPerPage)
			}
	
	override suspend fun getUser(userEmail: String): UserResponse? = withContext(ioDispatcher) {
		api.getUser(userEmail)
	}
	
	override suspend fun searchForUserByName(userName: String): List<UserResponse> =
			withContext(ioDispatcher) {
				api.searchForUserByName(userName)
			}
	
	override fun isUserLoggedIn(): Boolean = firebaseDataSource.isUserLoggedIn()
}