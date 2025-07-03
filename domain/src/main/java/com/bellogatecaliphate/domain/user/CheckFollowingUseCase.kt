package com.bellogatecaliphate.domain.user

import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class CheckFollowingUseCase @Inject constructor(
	private val userRepository: IUserRepository
) {
	
	suspend operator fun invoke(
		loggedInUserEmail: String,
		emailOfUserToCheckFollowingStatus: String
	): Boolean {
		return checkIfUserIsFollowing(loggedInUserEmail, emailOfUserToCheckFollowingStatus)
	}
	
	private suspend fun checkIfUserIsFollowing(
		loggedInUserEmail: String,
		emailOfUserToCheckFollowingStatus: String
	): Boolean {
		return userRepository.checkIfUserIsFollowing(
			loggedInUserEmail = loggedInUserEmail,
			emailOfUserToCheckFollowingStatus = emailOfUserToCheckFollowingStatus
		)
	}
	
}