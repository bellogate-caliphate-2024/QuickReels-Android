package com.bellogatecaliphate.user

import com.bellogatecaliphate.core.source.local.entity.UserEntity

interface IUserRepository {
	
	suspend fun getUser(): UserEntity?
}