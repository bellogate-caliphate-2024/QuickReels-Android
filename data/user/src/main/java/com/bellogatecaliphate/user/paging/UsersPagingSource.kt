package com.bellogatecaliphate.user.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bellogatecaliphate.user.remote.IRemoteDataSource
import com.bellogatecaliphate.user.remote.model.UserResponse
import javax.inject.Inject

internal class UsersPagingSource @Inject constructor(
	private val remoteSource: IRemoteDataSource
) : PagingSource<Int, UserResponse>() {
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> = try {
		// Start refresh at page 1 if undefined.
		val nextPage = params.key ?: 1
		val response = remoteSource.getUsers(nextPage, 10)
		val list = response?.listOfUsers ?: throw Exception()
		LoadResult.Page(
			data = list,
			prevKey = null,
			nextKey = response.nextPage ?: 1
		)
	}
	catch (e: Exception) {
		LoadResult.Error(e.fillInStackTrace())
	}
	
	override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
}