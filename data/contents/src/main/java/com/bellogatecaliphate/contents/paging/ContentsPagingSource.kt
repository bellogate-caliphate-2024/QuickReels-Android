package com.bellogatecaliphate.contents.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bellogatecaliphate.contents.remote.IRemoteSource
import com.bellogatecaliphate.contents.remote.model.ContentResponse
import javax.inject.Inject

internal class ContentsPagingSource @Inject constructor(
	private val remoteSource: IRemoteSource,
) : PagingSource<Int, ContentResponse>() {
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContentResponse> = try {
		// Start refresh at page 1 if undefined.
		val nextPage = params.key ?: 1
		val response = remoteSource.getContentsList(nextPage, 10)
		val list = response?.listOfContents ?: throw Exception()
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
	
	override fun getRefreshKey(state: PagingState<Int, ContentResponse>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
}