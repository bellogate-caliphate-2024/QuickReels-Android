package com.bellogatecaliphate.contents.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bellogatecaliphate.contents.remote.IRemoteSource
import com.bellogatecaliphate.contents.remote.model.ContentResponse
import com.bellogatecaliphate.user.IUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ContentsHistoryPagingSource @Inject constructor(
	private val userRepository: IUserRepository,
	private val ioDispatchers: CoroutineDispatcher,
	private val remoteSource: IRemoteSource
) : PagingSource<Int, ContentResponse>() {
	
	override suspend fun load(params: LoadParams<Int>):
			LoadResult<Int, ContentResponse> = withContext(ioDispatchers) {
		return@withContext try {
			// Start refresh at page 1 if undefined.
			val nextPage = params.key ?: 1
			val response = remoteSource.getContentsHistoryList(
				userRepository.getUserFromLocal()?.email ?: "",
				nextPage
			)
			val list = response?.listOfContents ?: throw Exception()
			val isLastPage = response.isLastPage != null && response.isLastPage
			LoadResult.Page(
				data = list,
				prevKey = null,
				nextKey = response.nextPage
				// In Paging 3.x, you indicate the end of pagination by setting the nextKey
				// (and prevKey for backward pagination) to null.
			)
		}
		catch (e: Exception) {
			LoadResult.Error(e.fillInStackTrace())
		}
	}
	
	override fun getRefreshKey(state: PagingState<Int, ContentResponse>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
	
	override val keyReuseSupported: Boolean
		get() = true
	
}