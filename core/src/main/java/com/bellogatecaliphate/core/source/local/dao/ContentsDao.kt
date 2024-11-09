package com.bellogatecaliphate.core.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.bellogatecaliphate.core.source.local.entity.ContentsEntity

@Dao
interface ContentsDao {
	
	@Query("SELECT * FROM contents WHERE page == :page")
	fun getPaginatedContents(page: Int): PagingSource<Int, ContentsEntity>
	
	@Query("DELETE FROM contents")
	suspend fun clearAll()
}