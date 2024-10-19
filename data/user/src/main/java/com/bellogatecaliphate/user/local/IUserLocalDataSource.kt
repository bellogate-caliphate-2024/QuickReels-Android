package com.bellogatecaliphate.user.local

import com.bellogatecaliphate.core.source.database.entity.UserEntity

interface IUserLocalDataSource {
	
	suspend fun getUser(): UserEntity?
}