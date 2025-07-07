package com.bellogatecaliphate.domain.contents

import com.bellogatecaliphate.contents.IContentsRepository
import com.bellogatecaliphate.domain.toContent
import javax.inject.Inject

class GetContentUseCase @Inject constructor(
	private val contentsRepository: IContentsRepository
) {
	
	suspend operator fun invoke(contentId: String) =
			contentsRepository.getContent(contentId)?.toContent()
	
}