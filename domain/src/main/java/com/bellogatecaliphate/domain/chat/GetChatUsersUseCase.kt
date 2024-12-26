package com.bellogatecaliphate.domain.chat

import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class GetChatUsersUseCase @Inject constructor(val userRepository: IUserRepository)