package com.bellogatecaliphate.domain.contents.like

import com.bellogatecaliphate.contents.IContentsRepository
import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class LikeContentUseCase @Inject constructor(
	private val contentRepository: IContentsRepository,
	private val userRepository: IUserRepository,
) {
	
	suspend operator fun invoke(contentId: String, isLiked: Boolean): Boolean {
		val userEmail = userRepository.getUserEmail()
		return contentRepository.likeContent(userEmail, contentId, isLiked)
	}
}