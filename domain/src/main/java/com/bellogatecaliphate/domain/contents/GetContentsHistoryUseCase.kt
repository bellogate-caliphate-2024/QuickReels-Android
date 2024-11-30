package com.bellogatecaliphate.domain.contents

import androidx.paging.map
import com.bellogatecaliphate.contents.IContentsRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetContentsHistoryUseCase @Inject constructor(private val repository: IContentsRepository) {
	
	operator fun invoke(userEmail: String) =
			repository.getPaginatedContentsHistory(userEmail)
	
	fun x(userEmail: String) =
			repository.getPaginatedContentsHistory(userEmail).flow.map { pagedData -> pagedData.map { } }
}