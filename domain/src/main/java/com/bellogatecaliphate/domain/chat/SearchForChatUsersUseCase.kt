package com.bellogatecaliphate.domain.chat

import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class SearchForChatUsersUseCase @Inject constructor(private val userRepository: IUserRepository) {
	
	suspend operator fun invoke(userNameToSearchFor: String): List<User> {
		val response = userRepository.searchForUserByName(userNameToSearchFor)
		return response.map { it.toUser() }
	}
}