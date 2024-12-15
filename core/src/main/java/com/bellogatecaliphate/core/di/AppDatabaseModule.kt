package com.bellogatecaliphate.core.di

import android.content.Context
import androidx.room.Room
import com.bellogatecaliphate.core.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object AppDatabaseModule {
	
	@Provides
	@Singleton
	fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
		val db = Room.databaseBuilder(
			applicationContext,
			AppDatabase::class.java, "database-name"
		).build()
		return db
	}
}