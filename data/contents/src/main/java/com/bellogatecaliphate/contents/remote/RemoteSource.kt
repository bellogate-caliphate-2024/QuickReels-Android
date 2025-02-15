package com.bellogatecaliphate.contents.remote

import com.bellogatecaliphate.contents.remote.api.ContentsApi
import com.bellogatecaliphate.contents.remote.model.ContentResponse
import com.bellogatecaliphate.contents.remote.model.ContentsListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteSource @Inject constructor(
	private val api: ContentsApi,
	private val ioDispatcher: CoroutineDispatcher
) : IRemoteSource {
	
	val listOfContent = listOf(
		ContentResponse(
			"119992299222",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/1.jpeg?alt=media&token=03fa3168-a47c-4f2a-b169-76505fbd364f",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am", "100",
			"70",
			"23",
			"",
			"Jeff Emuveyan    00000000000000000000",
			"",
			false
		),
		ContentResponse(
			"00000000",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/3.jpg?alt=media&token=a20702c8-b183-4d8f-8b27-f9da833b1319",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am", "100",
			"70",
			"23",
			"",
			"Jeff Emuveyan  111111111111111111111",
			"", false
		),
		ContentResponse(
			"112232322",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/3.jpg?alt=media&token=a20702c8-b183-4d8f-8b27-f9da833b1319",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am", "100",
			"70",
			"23",
			"",
			"Jeff Emuveyan  2222222222222222222",
			"", false
		),
		ContentResponse(
			"898989898",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/3.jpg?alt=media&token=a20702c8-b183-4d8f-8b27-f9da833b1319",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am", "100",
			"70",
			"23",
			"",
			"Jeff Emuveyan   333333333333333333 ",
			"", false
		),
		ContentResponse(
			"898989898",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/1.jpeg?alt=media&token=03fa3168-a47c-4f2a-b169-76505fbd364f",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am", "100",
			"70",
			"23",
			"",
			"Jeff Emuveyan 4444444444444444444444",
			"", false
		),
		ContentResponse(
			"898989898",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/1.jpeg?alt=media&token=03fa3168-a47c-4f2a-b169-76505fbd364f",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am", "100",
			"70",
			"23",
			"",
			"Jeff Emuveyan 555555555555555555555",
			"", false
		),
		ContentResponse(
			"898989898",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/3.jpg?alt=media&token=a20702c8-b183-4d8f-8b27-f9da833b1319",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am", "100",
			"70",
			"23",
			"",
			"Jeff Emuveyan 666666666666666666666",
			"", false
		),
		
		)
	
	val contentsListResponse = ContentsListResponse(
		1, 2, listOfContent, false
	)
	
	override suspend fun getContentsList(
		page: Int?,
		numberOfContentPerPage: Int
	): ContentsListResponse {
		return contentsListResponse
	}
	
	override suspend fun getContentsHistoryList(
		userEmail: String,
		page: Int,
		numberOfContentPerPage: Int
	): ContentsListResponse? {
		return null
	}
	
	override suspend fun likeContent(userEmail: String, contentId: String, isLiked: Boolean) =
			withContext(ioDispatcher) {
				api.likeContent(userEmail, contentId, isLiked) !!.isSuccess
			}
}