package com.bellogatecaliphate.domain.contents

import androidx.paging.map
import com.bellogatecaliphate.contents.IContentsRepository
import com.bellogatecaliphate.core.model.dto.Content
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetContentsHistoryUseCase @Inject constructor(private val repository: IContentsRepository) {
	
	operator fun invoke(userEmail: String) =
			repository.getPaginatedContentsHistory(userEmail).flow.map { pagingData ->
				pagingData.map {
					Content(
						it.id ?: "",
						it.videoUrl ?: "",
						it.thumbnailUrl ?: "",
						it.caption ?: "",
						it.date ?: "",
						it.numberOfViews ?: "",
						it.numberOfLikes ?: "",
						it.numberOfComments ?: "",
						it.userId ?: "",
						it.userName ?: "",
						it.userProfilePicture ?: ""
					)
				}
			}
}