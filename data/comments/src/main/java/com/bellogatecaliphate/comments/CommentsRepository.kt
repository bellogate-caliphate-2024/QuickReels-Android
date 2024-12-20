package com.bellogatecaliphate.comments

import com.bellogatecaliphate.comments.remote.IRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class CommentsRepository @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val remoteSource: IRemoteSource
) : ICommentsRepository