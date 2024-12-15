package com.bellogatecaliphate.contents.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bellogatecaliphate.contents.local.ILocalDataSource
import com.bellogatecaliphate.contents.remote.IRemoteSource
import com.bellogatecaliphate.contents.remote.model.ContentResponse
import com.bellogatecaliphate.core.source.local.entity.ContentEntity
import com.bellogatecaliphate.core.source.local.entity.ContentsListPageInfoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

private const val DEFAULT_PAGE = 1

@OptIn(ExperimentalPagingApi::class)
internal class ContentsRemoteMediator(
	private val ioDispatcher: CoroutineDispatcher,
	private val remoteSource: IRemoteSource,
	private val localDataSource: ILocalDataSource
) : RemoteMediator<Int, ContentEntity>() {
	
	@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, ContentEntity>
	): MediatorResult {
		// When the paging library run for the first time, the value of loadType will be REFRESH.
		// This signifies the initial loading of the first set of data.
		// This will only happen once and then the value will change to APPEND as the user scrolls down the list.
		
		return try {
			val pageToLoad = when (loadType) {
				LoadType.REFRESH -> {
					// When loadType is REFRESH it means the library wants to load data for the first time.
					// In this case, we want to start loading with the page number = 1
					DEFAULT_PAGE
				}
				
				LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true) // END PAGINATION
				LoadType.APPEND  -> {
					// APPEND is the second action to be performed by the pagination library after REFRESH action.
					// The value of loadType will be == APPEND each time the user scrolls towards the end of the list.
					// Now, the first thing we need to do in this case is to get the page number of the last item that was gotten from the
					// network and use it to make the next network call.
					// If lastItem is null it means no items were loaded after the initial REFRESH and there are
					// no more items to load.
					// Read more here: https://developer.android.com/topic/libraries/architecture/paging/v3-network-db#item-keys
					getRemoteKeyForLastContentItemOnTheList(state)?.nextPage
					?: return MediatorResult.Success(true)
				}
			}
			
			// NOTE, we don't need to wrap this network call in a withContext(ioDispatchers) because Retrofit already
			// does that for us. See https://stackoverflow.com/a/63986072
			val response = remoteSource.getContentsList(pageToLoad)
			if (response == null) {
				return MediatorResult.Error(Exception(""))
			} else {
				withContext(ioDispatcher) {
					localDataSource.deleteAllContents()
					localDataSource.deleteAllContentsListPageInfo()
					
					localDataSource.insert(
						ContentsListPageInfoEntity(response.currentPage, response.nextPage)
					)
					localDataSource.insert(getContent(response.listOfContents))
				}
			}
			
			// Now, we need to decide if there are more data to load or if the library should end the pagination process.
			// In our case, if the list response.isLastPage is true, it means we have finished loading all data from the backend. So we do:
			val endOfPageReached = response.isLastPage == true
			MediatorResult.Success(endOfPaginationReached = endOfPageReached)
			
			//NOTE: If endOfPageReached is false, the library will call this load() method once again
			// and loadType will be == APPEND. But if endOfPageReached == true, the library will stop pagination.
		}
		catch (e: IOException) {
			MediatorResult.Error(e)
		}
		catch (e: HttpException) {
			MediatorResult.Error(e)
		}
	}
	
	private fun getContent(listOfContents: List<ContentResponse>?): List<ContentEntity> {
		if (listOfContents == null) return emptyList()
		return listOfContents.map { it.toEntity() }
	}
	
	private suspend fun getRemoteKeyForLastContentItemOnTheList(state: PagingState<Int, ContentEntity>): ContentsListPageInfoEntity? =
			withContext(ioDispatcher) { localDataSource.getContentsListPageInfo() }
}