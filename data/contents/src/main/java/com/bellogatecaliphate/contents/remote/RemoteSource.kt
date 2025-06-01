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
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/every_james_bond_gun-barrel_turn-n-shoot_h264_46303.mp4?alt=media&token=e8aed77b-9dfc-48c0-bf58-1de130a0c211",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/1.jpeg?alt=media&token=03fa3168-a47c-4f2a-b169-76505fbd364f",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-200 - 10:30 am", "100",
			"70",
			"11",
			"",
			"Monday Manyy",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/p1.jpg?alt=media&token=630134db-35cb-41ea-9698-fa0cd25b80c0",
			false,
			false
		),
		ContentResponse(isAd = true),
		ContentResponse(
			"00000000",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/mad_max_the_nightrider_hd_h264_59681.mp4?alt=media&token=b8de37bd-3ee8-4185-bd9b-6f383bb84c27",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/3.jpg?alt=media&token=a20702c8-b183-4d8f-8b27-f9da833b1319",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-200 - 10:30 am",
			"100",
			"70",
			"0",
			"",
			"Tuesday Tuee",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/p2.jpg?alt=media&token=518d902-2efc-4739-8b96-00be0fff78f",
			false,
			false
		),
		ContentResponse(isAd = true),
		ContentResponse(
			"1120022",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/avatar_official_trailer_hd_h264_60552.mp4?alt=media&token=6da2c5fd-1bef-4328-ad6f-ec9fe40d6513",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/3.jpg?alt=media&token=a20702c8-b183-4d8f-8b27-f9da833b1319",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-200 - 10:30 am", "100",
			"70",
			"0",
			"",
			"Wednesday Wendy",
			"", false,
			false
		),
		ContentResponse(isAd = true),
		ContentResponse(
			"898989898",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/lord_of_the_rings_forbidden_edition_part_3_h264_60712.mp4?alt=media&token=ad7f0f2-851b-4fd2-b997-0cfb670fdf1d",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/3.jpg?alt=media&token=a20702c8-b183-4d8f-8b27-f9da833b1319",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-200 - 10:30 am", "100",
			"70",
			"0",
			"",
			"Thursday Thur",
			"", false,
			false
		),
		ContentResponse(
			"898989898",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/1.jpeg?alt=media&token=03fa3168-a47c-4f2a-b169-76505fbd364f",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-200 - 10:30 am", "100",
			"70",
			"0",
			"",
			"Friday Fry",
			"", false,
			false
		),
		ContentResponse(
			"898989898",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/1.jpeg?alt=media&token=03fa3168-a47c-4f2a-b169-76505fbd364f",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-200 - 10:30 am", "100",
			"70",
			"0",
			"",
			"Saturday Saturn",
			"", false,
			false
		),
		ContentResponse(
			"898989898",
			"",
			"https://firebasestorage.googleapis.com/v0/b/memo-24031.appspot.com/o/3.jpg?alt=media&token=a20702c8-b183-4d8f-8b27-f9da833b1319",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-200 - 10:30 am", "100",
			"70",
			"0",
			"",
			"Sunday Sunny",
			"", false,
			false
		),
		
		)
	
	val contentsListResponse = ContentsListResponse(
		1, null, listOfContent, true
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
	): ContentsListResponse {
		return contentsListResponse
	}
	
	override suspend fun likeContent(userEmail: String, contentId: String, isLiked: Boolean) =
			withContext(ioDispatcher) {
				api.likeContent(userEmail, contentId, isLiked) !!.isSuccess
			}
}