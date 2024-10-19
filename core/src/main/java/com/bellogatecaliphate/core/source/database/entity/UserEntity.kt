package com.bellogatecaliphate.core.source.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bellogatecaliphate.core.model.dto.User

@Entity(tableName = "user")
data class UserEntity(
	@PrimaryKey val id: String,
	val email: String
) {
	
	fun toUser(): User {
		return User(id, email)
	}
}