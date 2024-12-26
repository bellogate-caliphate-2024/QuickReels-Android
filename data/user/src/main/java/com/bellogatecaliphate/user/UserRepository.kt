package com.bellogatecaliphate.user

import com.bellogatecaliphate.core.source.local.entity.UserEntity
import com.bellogatecaliphate.user.local.IUserLocalDataSource
import com.bellogatecaliphate.user.remote.UserRemoteDataSource
import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse
import javax.inject.Inject

internal class UserRepository @Inject constructor(
	private val userLocalDataSource: IUserLocalDataSource,
	private val userRemoteDataSource: UserRemoteDataSource
) : IUserRepository {
	
	override suspend fun getUserFromLocal(): UserEntity? {
		return userLocalDataSource.getUser()
	}
	
	override suspend fun getUserFromRemote(email: String): UserResponse? {
		return userRemoteDataSource.getUser(email)
	}
	
	override suspend fun getAllUsers(page: Int): UsersResponse? {
		return userRemoteDataSource.getUsers(page)
	}
}