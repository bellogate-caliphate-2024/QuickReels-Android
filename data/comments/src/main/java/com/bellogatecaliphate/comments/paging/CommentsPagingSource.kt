package com.bellogatecaliphate.comments.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bellogatecaliphate.comments.remote.IRemoteSource
import com.bellogatecaliphate.comments.remote.model.CommentResponse
import javax.inject.Inject

internal class CommentsPagingSource @Inject constructor(
	private val remoteSource: IRemoteSource
) : PagingSource<Int, CommentResponse>() {
	
	var contentId: String = "N/A"
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommentResponse> =
			try {
				// Start refresh at page 1 if undefined.
				val nextPage = params.key ?: 1
				val response = remoteSource.getComments(contentId, nextPage, 4)
				val list = response?.comments ?: throw Exception()
				LoadResult.Page(
					data = list,
					prevKey = null,
					nextKey = response.nextPage ?: 1
				)
			}
			catch (e: Exception) {
				LoadResult.Error(e.fillInStackTrace())
			}
	
	override fun getRefreshKey(state: PagingState<Int, CommentResponse>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
}