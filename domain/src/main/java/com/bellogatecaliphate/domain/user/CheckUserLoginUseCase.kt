package com.bellogatecaliphate.domain.user

import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class CheckUserLoginUseCase @Inject constructor(private val userRepository: IUserRepository) {
	
	operator fun invoke(): Boolean {
		return userRepository.isUserLoggedIn()
	}
}