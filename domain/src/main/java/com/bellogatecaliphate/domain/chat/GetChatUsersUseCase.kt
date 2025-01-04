package com.bellogatecaliphate.domain.chat

import androidx.paging.PagingData
import androidx.paging.map
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.user.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetChatUsersUseCase @Inject constructor(private val userRepository: IUserRepository) {
	
	operator fun invoke(): Flow<PagingData<User>> =
			userRepository.getPaginatedUsersFromRemote().flow.map { pagingData ->
				pagingData.map {
					it.toUser()
				}
			}
}