package com.bellogatecaliphate.core.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bellogatecaliphate.core.source.database.dao.PostDao
import com.bellogatecaliphate.core.source.database.dao.UserDao
import com.bellogatecaliphate.core.source.database.entity.PostEntity
import com.bellogatecaliphate.core.source.database.entity.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	
	abstract fun postDao(): PostDao
	abstract fun userDao(): UserDao
}