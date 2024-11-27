package com.bellogatecaliphate.contents.remote

import com.bellogatecaliphate.contents.remote.model.ContentsListResponse

interface IRemoteSource {
	
	suspend fun getContentsList(page: Int?, numberOfContentPerPage: Int = 10): ContentsListResponse?
}