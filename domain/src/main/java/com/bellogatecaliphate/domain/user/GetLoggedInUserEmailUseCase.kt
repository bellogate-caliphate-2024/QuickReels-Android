package com.bellogatecaliphate.domain.user

import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class GetLoggedInUserEmailUseCase @Inject constructor(private val userRepository: IUserRepository) {
	
	suspend operator fun invoke(): String? {
		return userRepository.getUserEmail()
	}
}