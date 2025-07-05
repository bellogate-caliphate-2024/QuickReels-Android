package com.bellogatecaliphate.user.remote.api.di

import com.bellogatecaliphate.user.remote.api.UserApi
import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject

internal class UserApiImpl @Inject constructor(private val retrofit: Retrofit) : UserApi {
	
	override suspend fun getUsers(page: Int, numberOfContentPerPage: Int): UsersResponse? {
		return retrofit.create(UserApi::class.java).getUsers(page, numberOfContentPerPage)
	}
	
	override suspend fun getUser(userEmail: String): UserResponse? {
		return retrofit.create(UserApi::class.java).getUser(userEmail)
	}
	
	override suspend fun searchForUserByName(userName: String): List<UserResponse> {
		return retrofit.create(UserApi::class.java).searchForUserByName(userName)
	}
	
	override suspend fun followOrUnfollowUser(
		userEmail: String,
		emailOfUserToFollow: String,
		follow: Boolean
	): Boolean {
		return retrofit.create(UserApi::class.java).followOrUnfollowUser(
			userEmail = userEmail,
			emailOfUserToFollow = emailOfUserToFollow,
			follow = follow
		)
	}
	
	override suspend fun checkIfUserIsFollowing(
		loggedInUserEmail: String,
		emailOfUserToCheckFollowingStatus: String
	): Boolean {
		return retrofit.create(UserApi::class.java).checkIfUserIsFollowing(
			loggedInUserEmail = loggedInUserEmail,
			emailOfUserToCheckFollowingStatus = emailOfUserToCheckFollowingStatus
		)
	}
	
	override suspend fun saveNewAccountName(
		userEmail: String,
		newUserAccountName: String
	): Boolean {
		return retrofit.create(UserApi::class.java).saveNewAccountName(
			userEmail = userEmail,
			newUserAccountName = newUserAccountName
		)
	}
	
	override suspend fun changeProfilePicture(
		userEmail: String,
		newProfilePictureBase64Encoded: String
	): Boolean {
		return retrofit.create(UserApi::class.java).changeProfilePicture(
			userEmail = userEmail,
			newProfilePictureBase64Encoded = newProfilePictureBase64Encoded
		)
	}
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserApiModule {
	
	@Binds
	abstract fun bindApi(impl: UserApiImpl): UserApi
	
}