package com.bellogatecaliphate.comments.remote.api

internal interface CommentsApi {
	
	suspend fun saveComment()
	
	suspend fun deleteComment()
}