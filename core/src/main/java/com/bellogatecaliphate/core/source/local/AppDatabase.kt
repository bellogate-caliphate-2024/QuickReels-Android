package com.bellogatecaliphate.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bellogatecaliphate.core.source.local.dao.PostDao
import com.bellogatecaliphate.core.source.local.dao.UserDao
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.core.source.local.entity.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	
	abstract fun postDao(): PostDao
	abstract fun userDao(): UserDao
}