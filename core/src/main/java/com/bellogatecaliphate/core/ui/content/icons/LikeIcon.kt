package com.bellogatecaliphate.core.ui.content.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun LikeIcon(
	contentId: String,
	listOfLikedContentsFromLocal: MutableMap<String, Boolean?>,
	likedFromRemote: Boolean,
	existingNumberOfLikes: String?,
	onCLick: (contentId: String, isLiked: Boolean) -> Unit
) {
	val hasCachedLikedOrUnlikedValue = listOfLikedContentsFromLocal[contentId] != null
	
	val isLiked = if (hasCachedLikedOrUnlikedValue) {
		listOfLikedContentsFromLocal[contentId] !!
	} else likedFromRemote
	
	val latestNumberOfLikes = if (hasCachedLikedOrUnlikedValue) {
		val cachedLikedOrUnlikedValue = listOfLikedContentsFromLocal[contentId] !!
		val latestNumberOfLikes = if (likedFromRemote && cachedLikedOrUnlikedValue.not()) {
			existingNumberOfLikes?.toInt()?.minus(1)
		} else if (likedFromRemote.not() && cachedLikedOrUnlikedValue) {
			existingNumberOfLikes?.toInt()?.plus(1)
		} else if (likedFromRemote && cachedLikedOrUnlikedValue) {
			existingNumberOfLikes?.toInt()
		} else {
			existingNumberOfLikes?.toInt()
		}
		latestNumberOfLikes
	} else existingNumberOfLikes?.toInt()
	
	val icon = if (isLiked) R.drawable.baseline_favorite_24 else R.drawable.icon_heart
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			modifier = Modifier
				.size(PLACEHOLDER_24DP)
				.clickable {
					onCLick(contentId, ! isLiked)
				},
			painter = painterResource(id = icon),
			contentDescription = ""
		)
		if (latestNumberOfLikes != null) {
			Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
			Text(text = latestNumberOfLikes.toString(), style = MaterialTheme.typography.bodySmall)
		}
	}
}