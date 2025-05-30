package com.bellogatecaliphate.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bellogatecaliphate.core.source.local.entity.UserEntity
import com.bellogatecaliphate.user.local.ILocalDataSource
import com.bellogatecaliphate.user.paging.UsersPagingSource
import com.bellogatecaliphate.user.remote.IRemoteDataSource
import com.bellogatecaliphate.user.remote.model.UserResponse
import javax.inject.Inject

internal class UserRepository @Inject constructor(
	private val userLocalDataSource: ILocalDataSource,
	private val userRemoteDataSource: IRemoteDataSource,
	private val userPagingSource: UsersPagingSource
) : IUserRepository {
	
	override suspend fun getUserFromLocal(): UserEntity? {
		return userLocalDataSource.getUser()
	}
	
	override suspend fun getUserFromRemote(email: String): UserResponse? {
		return userRemoteDataSource.getUser(email)
	}
	
	override suspend fun searchForUserByName(userName: String): List<UserResponse> {
		return userRemoteDataSource.searchForUserByName(userName)
	}
	
	override fun getPaginatedUsersFromRemote(): Pager<Int, UserResponse> =
			Pager(PagingConfig(pageSize = 10)) { userPagingSource }
	
	override fun isUserLoggedIn(): Boolean = userRemoteDataSource.isUserLoggedIn()
}