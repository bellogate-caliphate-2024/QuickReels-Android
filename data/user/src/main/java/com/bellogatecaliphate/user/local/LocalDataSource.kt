package com.bellogatecaliphate.user.local

import com.bellogatecaliphate.core.source.local.dao.UserDao
import com.bellogatecaliphate.core.source.local.entity.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocalDataSource @Inject constructor(
	private val userDao: UserDao,
	private val ioDispatcher: CoroutineDispatcher
) : ILocalDataSource {
	
	override suspend fun getUser(): UserEntity? = withContext(ioDispatcher) {
		userDao.getUser()
	}
}