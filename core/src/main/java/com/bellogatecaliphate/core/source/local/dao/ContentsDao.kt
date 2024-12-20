package com.bellogatecaliphate.core.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bellogatecaliphate.core.source.local.entity.ContentEntity

@Dao
interface ContentsDao {
	
	@Query("SELECT * FROM contents")
	fun getPaginatedContents(): PagingSource<Int, ContentEntity>
	
	@Query("DELETE FROM contents")
	suspend fun deleteAll()
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(list: List<ContentEntity>)
}