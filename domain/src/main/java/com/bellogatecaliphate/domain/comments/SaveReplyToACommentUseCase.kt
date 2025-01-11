package com.bellogatecaliphate.domain.comments

import com.bellogatecaliphate.comments.ICommentsRepository
import javax.inject.Inject

class SaveReplyToACommentUseCase @Inject constructor(private val repository: ICommentsRepository) {
	
	suspend operator fun invoke(originalCommentId: String, reply: String): Boolean {
		val response = repository.saveReply(originalCommentId, reply)
		return response?.saved ?: false
	}
}