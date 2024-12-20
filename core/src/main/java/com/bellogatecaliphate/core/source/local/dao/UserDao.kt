package com.bellogatecaliphate.core.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.bellogatecaliphate.core.source.local.entity.UserEntity

@Dao
interface UserDao {
	
	@Query("SELECT * FROM user")
	suspend fun getUser(): UserEntity?
}