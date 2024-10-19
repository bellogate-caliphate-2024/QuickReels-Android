package com.bellogatecaliphate.user

import com.bellogatecaliphate.core.source.database.entity.UserEntity
import com.bellogatecaliphate.user.local.IUserLocalDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(
	private val userLocalDataSource: IUserLocalDataSource
) : IUserRepository {
	
	override suspend fun getUser(): UserEntity? {
		return userLocalDataSource.getUser()
	}
}