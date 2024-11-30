package com.bellogatecaliphate.contents.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bellogatecaliphate.contents.remote.IRemoteSource
import com.bellogatecaliphate.contents.remote.model.ContentResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ContentsHistoryPagingSource @Inject constructor(
	private val userEmail: String,
	private val ioDispatchers: CoroutineContext,
	private val remoteSource: IRemoteSource
) : PagingSource<Int, ContentResponse>() {
	
	override suspend fun load(params: LoadParams<Int>):
			LoadResult<Int, ContentResponse> = withContext(ioDispatchers) {
		return@withContext try {
			// Start refresh at page 1 if undefined.
			val nextPage = params.key ?: 1
			val response = remoteSource.getContentsHistoryList(userEmail, nextPage)
			val list = response?.listOfContents ?: throw Exception()
			LoadResult.Page(
				data = list,
				prevKey = null,
				nextKey = response.nextPage ?: 1
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