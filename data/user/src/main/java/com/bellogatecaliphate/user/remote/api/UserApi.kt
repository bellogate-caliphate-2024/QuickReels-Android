package com.bellogatecaliphate.user.remote.api

import com.bellogatecaliphate.user.remote.model.UserResponse
import com.bellogatecaliphate.user.remote.model.UsersResponse
import retrofit2.http.GET

interface UserApi {
	
	@GET("get_users")
	suspend fun getUsers(page: Int, numberOfContentPerPage: Int): UsersResponse?
	
	@GET("get_user")
	suspend fun getUser(userEmail: String): UserResponse?
	
}