package com.bellogatecaliphate.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
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
	
	override fun getUserEmail(): String? = userRemoteDataSource.getUserEmail()
	
	override suspend fun getUserFromRemote(email: String): UserResponse? {
		return userRemoteDataSource.getUser(email)
	}
	
	override suspend fun searchForUserByName(userName: String): List<UserResponse> {
		return userRemoteDataSource.searchForUserByName(userName)
	}
	
	override fun getPaginatedUsersFromRemote(): Pager<Int, UserResponse> =
			Pager(PagingConfig(pageSize = 10)) { userPagingSource }
	
	override fun isUserLoggedIn(): Boolean = userRemoteDataSource.isUserLoggedIn()
	
	override suspend fun followOrUnfollowUser(
		userEmail: String,
		emailOfUserToFollow: String,
		follow: Boolean
	): Boolean {
		return userRemoteDataSource.followOrUnfollowUser(
			userEmail = userEmail,
			emailOfUserToFollow = emailOfUserToFollow,
			follow = follow
		)
	}
	
	override suspend fun checkIfUserIsFollowing(
		loggedInUserEmail: String,
		emailOfUserToCheckFollowingStatus: String
	): Boolean {
		return userRemoteDataSource.checkIfUserIsFollowing(
			loggedInUserEmail = loggedInUserEmail,
			emailOfUserToCheckFollowingStatus = emailOfUserToCheckFollowingStatus
		)
	}
	
	override suspend fun saveNewAccountName(
		userEmail: String,
		newUserAccountName: String
	): Boolean {
		return userRemoteDataSource.saveNewAccountName(
			userEmail = userEmail,
			newUserAccountName = newUserAccountName
		)
	}
	
	override suspend fun changeProfilePicture(
		userEmail: String,
		newProfilePictureBase64Encoded: String
	): Boolean {
		return userRemoteDataSource.changeProfilePicture(
			userEmail = userEmail,
			newProfilePictureBase64Encoded = newProfilePictureBase64Encoded
		)
	}
}