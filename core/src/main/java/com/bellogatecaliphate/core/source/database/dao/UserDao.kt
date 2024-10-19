package com.bellogatecaliphate.core.source.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.bellogatecaliphate.core.source.database.entity.UserEntity

@Dao
interface UserDao {
	
	@Query("SELECT * FROM user")
	suspend fun getUser(): UserEntity?
}