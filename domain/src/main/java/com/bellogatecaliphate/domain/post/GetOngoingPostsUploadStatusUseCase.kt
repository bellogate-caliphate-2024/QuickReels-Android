package com.bellogatecaliphate.domain.post

import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.core.source.local.entity.PostEntity
import com.bellogatecaliphate.post.IPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOngoingPostsUploadStatusUseCase @Inject constructor(
	private val postRepository: IPostRepository
) {
	
	suspend operator fun invoke(): Flow<List<Post>> {
		return postRepository.getOngoingPostsUploadStatus(PostEntity.UploadStatus.Success)
			.map { it ->
				it.map {
					Post(
						id = it.id,
						videoFilePath = it.videoFilePath,
						userId = it.userId,
						time = it.time,
						caption = it.caption,
						thumbnailFilePath = it.thumbnailBase64String,
						uploadProgressPercentage = it.uploadStatus.uploadProgressPercentage.toString(),
						isUploading = it.uploadStatus is PostEntity.UploadStatus.InProgress,
						isUploaded = it.uploadStatus is PostEntity.UploadStatus.Success,
						isUploadFailed = it.uploadStatus is PostEntity.UploadStatus.Failed
					)
				}
			}
	}
}