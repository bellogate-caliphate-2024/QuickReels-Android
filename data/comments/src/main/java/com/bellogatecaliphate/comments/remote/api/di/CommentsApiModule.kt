package com.bellogatecaliphate.comments.remote.api.di

import com.bellogatecaliphate.comments.remote.api.CommentsApi
import com.bellogatecaliphate.comments.remote.model.CommentsListResponse
import com.bellogatecaliphate.comments.remote.model.SaveReplyToCommentResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject

internal class CommentsApiImpl @Inject constructor(private val retrofit: Retrofit) : CommentsApi {
	
	override suspend fun getComments(
		contentId: String,
		page: Int,
		numberOfCommentsPerPage: Int
	): CommentsListResponse? {
		return retrofit.create(CommentsApi::class.java)
			.getComments(contentId, page, numberOfCommentsPerPage)
	}
	
	override suspend fun getCommentReplies(commentId: String, page: Int): CommentsListResponse? {
		return retrofit.create(CommentsApi::class.java).getCommentReplies(commentId, page)
	}
	
	override suspend fun saveReply(
		originalCommentId: String,
		reply: String
	): SaveReplyToCommentResponse? {
		return retrofit.create(CommentsApi::class.java).saveReply(originalCommentId, reply)
	}
	
	override suspend fun deleteComment(commentId: String): Boolean {
		return retrofit.create(CommentsApi::class.java).deleteComment(commentId)
	}
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CommentsApiModule {
	
	@Binds
	abstract fun bindApi(impl: CommentsApiImpl): CommentsApi
}