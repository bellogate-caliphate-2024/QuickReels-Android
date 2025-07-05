package com.bellogatecaliphate.domain.contents

import com.bellogatecaliphate.contents.IContentsRepository
import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
import javax.inject.Inject

class EditContentCaptionUseCase @Inject constructor(
	private val getUserEmailUseCase: GetLoggedInUserEmailUseCase,
	private val contentsRepository: IContentsRepository
) {
	
	suspend operator fun invoke(
		contentId: String,
		newCaption: String
	): Boolean {
		val userEmail = getUserEmailUseCase() ?: ""
		return contentsRepository.editContentCaption(userEmail, contentId, newCaption)
	}
}