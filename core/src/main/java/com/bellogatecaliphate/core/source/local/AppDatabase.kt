package com.bellogatecaliphate.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bellogatecaliphate.core.source.local.dao.ContentsDao
import com.bellogatecaliphate.core.source.local.dao.ContentsListPageInfoDao
import com.bellogatecaliphate.core.source.local.dao.PostDao
import com.bellogatecaliphate.core.source.local.dao.UserDao
import com.bellogatecaliphate.core.source.local.entity.ContentEntity
import com.bellogatecaliphate.core.source.local.entity.ContentsListPageInfoEntity
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.core.source.local.entity.UserEntity

@Database(
	entities = [
		PostEntity::class,
		UserEntity::class,
		ContentEntity::class,
		ContentsListPageInfoEntity::class],
	version = 1
)
abstract class AppDatabase : RoomDatabase() {
	
	abstract fun postDao(): PostDao
	abstract fun userDao(): UserDao
	abstract fun contentsDao(): ContentsDao
	abstract fun contentsListPageInfoDao(): ContentsListPageInfoDao
}