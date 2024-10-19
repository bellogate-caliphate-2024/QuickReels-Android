package com.bellogatecaliphate.domain.user

import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val userRepository: IUserRepository) {
	
	suspend operator fun invoke(): User? {
		return userRepository.getUser()?.toUser()
	}
}