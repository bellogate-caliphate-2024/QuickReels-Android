package com.bellogatecaliphate.domain.user

import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val userRepository: IUserRepository) {
	
	suspend operator fun invoke(): User? {
		val userEmail = userRepository.getUserEmail()
		val user = userRepository.getUserFromRemote(userEmail)
		return user?.toUser()
	}
}