package com.bellogatecaliphate.user

import com.bellogatecaliphate.core.source.database.entity.UserEntity

interface IUserRepository {
	
	suspend fun getUser(): UserEntity?
}