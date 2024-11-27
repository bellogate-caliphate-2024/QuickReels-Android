package com.bellogatecaliphate.core.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contents_list_page_info")
class ContentsListPageInfoEntity(@PrimaryKey val currentPage: Int?, val nextPage: Int?)