package com.bellogatecaliphate.timeline.ui.content

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Advert
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.ui.content.ContentListItem
import com.bellogatecaliphate.nativeads.QuickReelsNativeAd

@Composable
internal fun ContentsList(
	list: LazyPagingItems<Content>?,
	advert: Advert?,
	firstVisibleItemIndex: Int,
	firstVisibleItemScrollOffset: Int,
	mapOfLikedAndUnlikedContents: MutableMap<String, Boolean?>,
	onAdRequest: () -> Unit,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit,
	onSaveScrollPosition: (index: Int, offset: Int) -> Unit,
	onOpenAccountDetails: (accountUserEmail: String) -> Unit,
	onDownloadClicked: () -> Unit = {},
) {
	if (list == null) return
	val listState = remember {
		LazyListState(firstVisibleItemIndex, firstVisibleItemScrollOffset)
	}
	// Track the previous index and scroll position
	val previousIndex = remember { mutableIntStateOf(0) }
	val previousOffset = remember { mutableIntStateOf(0) }
	val canScroll = remember { mutableStateOf(true) }
	var restored by remember { mutableStateOf(false) }
	
	// Monitor scroll changes using LaunchedEffect
	val currentIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
	val currentOffset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
	
	LaunchedEffect(currentIndex, currentOffset) {
		if (currentIndex == previousIndex.intValue && currentOffset > previousOffset.intValue) {
			// The user is scrolling down the list to the bottom:
			val nextItemIndex = listState.firstVisibleItemIndex + 1
			listState.scrollToItem(nextItemIndex)
			
		} else if (currentIndex == previousIndex.intValue && currentOffset < previousOffset.intValue) {
			// The user is scrolling up the list to the top:
			val previousItemIndex = listState.firstVisibleItemIndex
			if (previousItemIndex >= 0) {
				listState.scrollToItem(previousItemIndex)
			}
		}
		
		// Update the previous scroll state for the next check
		canScroll.value = false
		previousIndex.intValue = currentIndex
		previousOffset.intValue = currentOffset
		
		// Save list scroll position when user scrolls so that we can use it to preserve the list position:
		if (! restored) {
			restored = true // prevent collecting scroll position too early
		} else {
			snapshotFlow {
				listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset
			}.collect { (index, offset) ->
				onSaveScrollPosition(index, offset)
			}
		}
	}
	
	LazyColumn(
		state = listState, modifier = Modifier
			.fillMaxSize()
			.scrollable(
				state = rememberScrollState(),
				enabled = canScroll.value,
				orientation = Orientation.Vertical
			)
	) {
		items(list.itemCount) { index ->
			val content = list[index] ?: return@items
			if (content is Advert) {
				NativeAd(Modifier.fillParentMaxSize(), advert, onAdRequest)
			} else {
				ContentListItem(
					modifier = Modifier.fillParentMaxSize(),
					content = content,
					mapOfLikedAndUnlikedContents = mapOfLikedAndUnlikedContents,
					contentBelongsToLoggedInUser = false,
					onLikeButtonPressed = onLikeButtonPressed,
					onCommentButtonPressed = onCommentButtonPressed,
					onOpenAccountDetails = onOpenAccountDetails,
					onDownloadClicked = onDownloadClicked
				)
			}
		}
	}
}

@Composable
private fun NativeAd(modifier: Modifier, ad: Advert?, onAdRequest: () -> Unit) {
	LaunchedEffect(Unit) {
		onAdRequest()
	}
	QuickReelsNativeAd(modifier, ad?.nativeAd)
}