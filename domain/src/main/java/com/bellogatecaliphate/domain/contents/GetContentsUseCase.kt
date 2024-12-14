package com.bellogatecaliphate.domain.contents

import androidx.paging.PagingData
import androidx.paging.map
import com.bellogatecaliphate.contents.IContentsRepository
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.source.local.entity.ContentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetContentsUseCase @Inject constructor(private val repository: IContentsRepository) {
	
	operator fun invoke(): Flow<PagingData<Content>> {
		return repository.getPaginatedContents(1).flow.map {
			it.map { contentEntity ->
				contentEntity.toContent()
			}
		}
	}
	
	private fun ContentEntity.toContent(): Content {
		return Content(
			id ?: "",
			videoUrl ?: "",
			thumbnailUrl ?: "",
			caption ?: "",
			date ?: "",
			numberOfViews ?: "",
			numberOfLikes ?: "",
			numberOfComments ?: "",
			userId ?: "",
			userName ?: "",
			userProfilePicture ?: ""
		)
	}
}