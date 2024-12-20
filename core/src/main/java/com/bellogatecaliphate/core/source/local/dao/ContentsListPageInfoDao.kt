package com.bellogatecaliphate.core.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bellogatecaliphate.core.source.local.entity.ContentsListPageInfoEntity

@Dao
interface ContentsListPageInfoDao {
	
	@Query("SELECT * FROM contents_list_page_info")
	suspend fun getContentsListPage(): ContentsListPageInfoEntity?
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(contentsListPageInfoEntity: ContentsListPageInfoEntity)
	
	@Query("DELETE FROM contents_list_page_info")
	suspend fun deleteAll()
}
