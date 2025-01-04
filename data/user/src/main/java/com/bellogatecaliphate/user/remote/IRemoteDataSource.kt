package com.bellogatecaliphate.user.remote

import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse

internal interface IRemoteDataSource {
	
	suspend fun getUsers(page: Int, numberOfUsersPerPage: Int): UsersResponse?
	
	suspend fun getUser(userEmail: String): UserResponse?
	
	suspend fun searchForUserByName(userName: String): List<UserResponse>
}