package com.bellogatecaliphate.contents.paging.di

import com.bellogatecaliphate.contents.paging.ContentsHistoryPagingSource
import dagger.assisted.AssistedFactory

@AssistedFactory
internal interface ContentsHistoryPagingSourceFactory {
	fun create(userEmail: String): ContentsHistoryPagingSource
}