package com.bellogatecaliphate.domain.contents

import com.bellogatecaliphate.contents.IContentsRepository
import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
import javax.inject.Inject

class DeleteContentUseCase @Inject constructor(
	private val getUserEmailUseCase: GetLoggedInUserEmailUseCase,
	private val contentsRepository: IContentsRepository
) {
	
	suspend operator fun invoke(
		contentId: String
	): Boolean {
		val userEmail = getUserEmailUseCase() ?: ""
		return contentsRepository.deleteContent(userEmail, contentId)
	}
}