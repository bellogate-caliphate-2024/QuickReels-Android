package com.bellogatecaliphate.domain.post

import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.domain.user.GetUserInfoUseCase
import com.bellogatecaliphate.post.IPostRepository
import javax.inject.Inject

class EnQueuePostUseCase @Inject constructor(
	private val getUserInfoUseCase: GetUserInfoUseCase,
	private val postRepository: IPostRepository
) {
	
	suspend operator fun invoke(post: Post) {
		postRepository.uploadPost(
			post.id,
			post.videoFilePath,
			getUserInfoUseCase()?.email ?: "",
			post.time,
			post.caption,
			post.thumbnailFilePath ?: ""
		)
	}
}