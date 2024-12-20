package com.bellogatecaliphate.core.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(postEntity: PostEntity)
	
	@Delete
	suspend fun delete(postEntity: PostEntity)
	
	@Query("SELECT * FROM posts WHERE status = :status")
	fun getPostsByStatus(status: PostEntity.Status): Flow<List<PostEntity>>
	
	@Query("SELECT * FROM posts WHERE id = :id")
	fun getPostById(id: String): PostEntity?
}