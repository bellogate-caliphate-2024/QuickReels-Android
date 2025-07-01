package com.bellogatecaliphate.user.remote

import com.bellogatecaliphate.user.remote.api.UserApi
import com.bellogatecaliphate.user.remote.firebase.FirebaseDataSource
import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteDataSource @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val firebaseDataSource: FirebaseDataSource,
	private val api: UserApi
) : IRemoteDataSource {
	
	override fun getUserEmail(): String? = firebaseDataSource.getUserEmail()
	
	override suspend fun getUsers(page: Int, numberOfUsersPerPage: Int): UsersResponse? =
			withContext(ioDispatcher) {
				api.getUsers(page, numberOfUsersPerPage)
			}
	
	override suspend fun getUser(userEmail: String): UserResponse = withContext(ioDispatcher) {
		//api.getUser(userEmail)
		delay(3_000)
		UserResponse(
			email = userEmail,
			accountName = "Jeff Emuveyan",
			profilePictureUrl = "https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/p1.jpg?alt=media&token=630134db-35cb-41ea-9698-fa0cd25b80c0",
			numberOfLikes = "100",
			numberOfViews = "15k",
			numberOfFollowers = "45k",
			numberOfFollowing = "30"
		)
	}
	
	override suspend fun searchForUserByName(userName: String): List<UserResponse> =
			withContext(ioDispatcher) {
				api.searchForUserByName(userName)
			}
	
	override fun isUserLoggedIn(): Boolean = firebaseDataSource.isUserLoggedIn()
}