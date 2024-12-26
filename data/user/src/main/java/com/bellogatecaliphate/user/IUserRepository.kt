package com.bellogatecaliphate.user

import com.bellogatecaliphate.core.source.local.entity.UserEntity
import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse

interface IUserRepository {
	
	suspend fun getUserFromLocal(): UserEntity?
	
	suspend fun getUserFromRemote(email: String): UserResponse?
	
	suspend fun getAllUsers(page: Int): UsersResponse?
}