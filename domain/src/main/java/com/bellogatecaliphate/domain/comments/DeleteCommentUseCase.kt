package com.bellogatecaliphate.domain.comments

import com.bellogatecaliphate.comments.ICommentsRepository
import javax.inject.Inject

class DeleteCommentUseCase @Inject constructor(
	private val repository: ICommentsRepository
) {
	
	suspend operator fun invoke(commentId: String): Boolean {
		val response = repository.deleteComment(commentId)
		return response
	}
}