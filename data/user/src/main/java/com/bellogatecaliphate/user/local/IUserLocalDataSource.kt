package com.bellogatecaliphate.user.local

import com.bellogatecaliphate.core.source.local.entity.UserEntity

interface IUserLocalDataSource {
	
	suspend fun getUser(): UserEntity?
}