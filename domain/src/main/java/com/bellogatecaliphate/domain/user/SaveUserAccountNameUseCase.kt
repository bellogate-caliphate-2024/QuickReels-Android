package com.bellogatecaliphate.domain.user

import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class SaveUserAccountNameUseCase @Inject constructor(
	private val userRepository: IUserRepository
) {
	
	suspend operator fun invoke(
		userEmail: String,
		newUserAccountName: String
	): Boolean {
		return userRepository.saveNewAccountName(
			userEmail = userEmail,
			newUserAccountName = newUserAccountName
		)
	}
	
}