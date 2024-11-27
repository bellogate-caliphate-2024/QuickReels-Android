package com.bellogatecaliphate.contents.remote.model

data class ContentsListResponse(
	val currentPage: Int?,
	val nextPage: Int?,
	val listOfContents: List<ContentResponse>?,
	val isLastPage: Boolean? // Discuss with BE to return false for this value when there are no more pages to load.
)