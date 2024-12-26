package com.bellogatecaliphate.user.local

import com.bellogatecaliphate.core.source.local.entity.UserEntity

interface ILocalDataSource {
	
	suspend fun getUser(): UserEntity?
}