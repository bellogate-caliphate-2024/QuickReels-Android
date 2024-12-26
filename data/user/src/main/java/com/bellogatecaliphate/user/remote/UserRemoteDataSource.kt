package com.bellogatecaliphate.user.remote

import com.bellogatecaliphate.user.remote.api.UserApi
import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class UserRemoteDataSource @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val api: UserApi
) : IUserRemoteDataSource {
	
	override suspend fun getUsers(page: Int): UsersResponse? = withContext(ioDispatcher) {
		api.getUsers(page)
	}
	
	override suspend fun getUser(userEmail: String): UserResponse? = withContext(ioDispatcher) {
		api.getUser(userEmail)
	}
}