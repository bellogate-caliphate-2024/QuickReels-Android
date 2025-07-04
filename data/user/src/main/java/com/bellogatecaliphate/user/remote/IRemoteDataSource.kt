package com.bellogatecaliphate.user.remote

import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse

internal interface IRemoteDataSource {
	
	fun getUserEmail(): String?
	
	suspend fun getUsers(page: Int, numberOfUsersPerPage: Int): UsersResponse?
	
	suspend fun getUser(userEmail: String): UserResponse?
	
	suspend fun searchForUserByName(userName: String): List<UserResponse>
	
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
	
	suspend fun saveNewAccountName(
		userEmail: String,
		newUserAccountName: String
	): Boolean
	
}