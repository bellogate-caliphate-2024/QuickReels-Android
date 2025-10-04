package com.bellogatecaliphate.domain.comments

import com.bellogatecaliphate.comments.ICommentsRepository
import com.bellogatecaliphate.comments.remote.model.AddCommentRequest
import com.bellogatecaliphate.core.model.dto.Comment
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(private val repository: ICommentsRepository) {
	
	suspend operator fun invoke(
		contentId: String,
		parentCommentId: String?,
		comment: Comment
	): Boolean {
		return repository.addComment(
			contentId = contentId,
			parentCommentId = parentCommentId,
			comment = AddCommentRequest(
				commentId = comment.commentId,
				userId = comment.userId,
				userProfilePictureUrl = comment.userProfilePictureUrl,
				text = comment.text,
				date = comment.date,
				numberOfReplies = comment.numberOfReplies,
				isReply = comment.isReply,
				parentCommentId = comment.parentCommentId
			)
		)
	}
}