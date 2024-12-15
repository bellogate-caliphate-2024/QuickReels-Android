package com.bellogatecaliphate.core.source.local.dao.di

import com.bellogatecaliphate.core.source.local.AppDatabase
import com.bellogatecaliphate.core.source.local.dao.UserDao
import com.bellogatecaliphate.core.source.local.entity.UserEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class)
abstract class UserDaoModule {
	
	class UserDaoImp @Inject constructor(private val appDatabase: AppDatabase) : UserDao {
		
		override suspend fun getUser(): UserEntity? {
			return appDatabase.userDao().getUser()
		}
	}
	
	@Binds
	abstract fun bindPostDao(impl: UserDaoImp): UserDao
}