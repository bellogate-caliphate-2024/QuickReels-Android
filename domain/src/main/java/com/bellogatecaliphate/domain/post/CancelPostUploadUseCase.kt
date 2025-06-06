package com.bellogatecaliphate.domain.post

import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.post.IPostRepository
import javax.inject.Inject

class CancelPostUploadUseCase @Inject constructor(
	private val postRepository: IPostRepository
) {
	
	suspend operator fun invoke(post: Post) {
		return postRepository.cancelUploadingPost(post.id)
	}
}