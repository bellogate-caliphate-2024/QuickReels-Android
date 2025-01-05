package com.bellogatecaliphate.user.remote.api

import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
	
	@GET("get_users")
	suspend fun getUsers(
		@Query("page") page: Int,
		@Query("numberOfContentPerPage") numberOfContentPerPage: Int
	): UsersResponse?
	
	@GET("get_user")
	suspend fun getUser(userEmail: String): UserResponse?
	
	@GET("search_for_user")
	suspend fun searchForUserByName(@Query("userName") userName: String): List<UserResponse>
}