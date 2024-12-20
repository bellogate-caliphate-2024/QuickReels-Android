package com.bellogatecaliphate.contents

import androidx.paging.Pager
import com.bellogatecaliphate.contents.remote.model.ContentResponse
import com.bellogatecaliphate.core.source.local.entity.ContentEntity

interface IContentsRepository {
	fun getPaginatedContents(page: Int): Pager<Int, ContentEntity>
	fun getPaginatedContentsHistory(userEmail: String): Pager<Int, ContentResponse>
}