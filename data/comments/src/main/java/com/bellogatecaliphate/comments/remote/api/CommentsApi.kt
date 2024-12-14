package com.bellogatecaliphate.comments.remote.api

interface CommentsApi {
	
	suspend fun saveComment()
	
	suspend fun deleteComment()
}