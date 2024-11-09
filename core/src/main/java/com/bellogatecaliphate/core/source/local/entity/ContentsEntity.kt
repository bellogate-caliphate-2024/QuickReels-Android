package com.bellogatecaliphate.core.source.local.entity

import androidx.room.Entity

@Entity(tableName = "contents")
data class ContentsEntity(
	val page: Int
)