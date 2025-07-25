package com.bellogatecaliphate.domain.comments

import com.bellogatecaliphate.comments.ICommentsRepository
import com.bellogatecaliphate.core.model.dto.Comment
import javax.inject.Inject

class GetCommentRepliesUseCase @Inject constructor(private val repository: ICommentsRepository) {
	
	suspend operator fun invoke(commentId: String, page: Int): Pair<Boolean, List<Comment>?> {
		val result = repository.getCommentReplies(commentId, page)
		val isLastPage = result?.isLastPage ?: false
		val list = result?.comments?.map {
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
		return Pair(isLastPage, list)
	}
}