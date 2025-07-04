package com.bellogatecaliphate.domain.user

import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class FollowUserUseCase @Inject constructor(
	private val userRepository: IUserRepository
) {
	suspend operator fun invoke(
		userEmail: String,
		emailOfUserToFollow: String,
		follow: Boolean
	): Boolean {
		return userRepository.followOrUnfollowUser(userEmail, emailOfUserToFollow, follow)
	}
}