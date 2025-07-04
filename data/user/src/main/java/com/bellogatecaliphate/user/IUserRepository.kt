package com.bellogatecaliphate.user

import androidx.paging.Pager
import com.bellogatecaliphate.user.remote.model.UserResponse

interface IUserRepository {
	
	fun getUserEmail(): String?
	
	suspend fun getUserFromRemote(email: String): UserResponse?
	
	suspend fun searchForUserByName(userName: String): List<UserResponse>
	
	fun getPaginatedUsersFromRemote(): Pager<Int, UserResponse>
	
	fun isUserLoggedIn(): Boolean
	
	suspend fun followOrUnfollowUser(
		userEmail: String,
		emailOfUserToFollow: String,
		follow: Boolean
	): Boolean
	
	suspend fun checkIfUserIsFollowing(
		loggedInUserEmail: String,
		emailOfUserToCheckFollowingStatus: String
	): Boolean
}