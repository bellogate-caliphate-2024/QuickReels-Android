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
	
	override suspend fun getUsers(page: Int): UsersResponse? {
		return retrofit.create(UserApi::class.java).getUsers(page)
	}
	
	override suspend fun getUser(userEmail: String): UserResponse? {
		return retrofit.create(UserApi::class.java).getUser(userEmail)
	}
	
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserApiModule {
	
	@Binds
	abstract fun bindApi(impl: UserApiImpl): UserApi
	
}