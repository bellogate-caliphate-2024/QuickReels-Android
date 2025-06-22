package com.bellogatecaliphate.user

import androidx.paging.Pager
import com.bellogatecaliphate.user.remote.model.UserResponse

interface IUserRepository {
	
	fun getUserEmail(): String?
	
	suspend fun getUserFromRemote(email: String): UserResponse?
	
	suspend fun searchForUserByName(userName: String): List<UserResponse>
	
	fun getPaginatedUsersFromRemote(): Pager<Int, UserResponse>
	
	fun isUserLoggedIn(): Boolean
}