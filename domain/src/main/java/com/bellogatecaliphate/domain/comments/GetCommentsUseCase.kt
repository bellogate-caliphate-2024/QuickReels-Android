package com.bellogatecaliphate.domain.comments

import androidx.paging.PagingData
import androidx.paging.map
import com.bellogatecaliphate.comments.ICommentsRepository
import com.bellogatecaliphate.core.model.dto.Comment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
	private val repository: ICommentsRepository
) {
	
	operator fun invoke(contentId: String): Flow<PagingData<Comment>> =
			repository.getPaginatedComments(contentId).map { pagingData ->
				pagingData.map {
					Comment(
						it.commentId ?: "",
						it.userId ?: "",
						it.userProfilePictureUrl ?: "",
						it.text ?: "",
						it.date ?: "",
						it.numberOfReplies,
						it.isReply,
						it.parentCommentId
					)
				}
			}
}