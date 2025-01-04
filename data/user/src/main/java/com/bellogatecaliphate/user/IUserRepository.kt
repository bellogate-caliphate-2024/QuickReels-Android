package com.bellogatecaliphate.user

import androidx.paging.Pager
import com.bellogatecaliphate.core.source.local.entity.UserEntity
import com.bellogatecaliphate.user.remote.model.UserResponse

interface IUserRepository {
	
	suspend fun getUserFromLocal(): UserEntity?
	
	suspend fun getUserFromRemote(email: String): UserResponse?
	
	suspend fun searchForUserByName(userName: String): List<UserResponse>
	
	fun getPaginatedUsersFromRemote(): Pager<Int, UserResponse>
}