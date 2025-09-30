package com.bellogatecaliphate.domain.post

import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.post.IPostRepository
import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class EnQueuePostUseCase @Inject constructor(
	private val userRepository: IUserRepository,
	private val postRepository: IPostRepository
) {
	
	suspend operator fun invoke(post: Post) {
		postRepository.uploadPost(
			post.id,
			post.videoFilePath,
			userRepository.getUserEmail() ?: "",
			post.time,
			post.caption,
			post.thumbnailFilePath ?: ""
		)
	}
}