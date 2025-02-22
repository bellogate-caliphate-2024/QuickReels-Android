package com.bellogatecaliphate.timeline.ui.content

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Content

@Composable
internal fun Contents(
	list: LazyPagingItems<Content>?,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: (contentId: String) -> Unit
) {
	if (list == null) return
	val listState = rememberLazyListState()
	// Track the previous index and scroll position
	val previousIndex = remember { mutableIntStateOf(0) }
	val previousOffset = remember { mutableIntStateOf(0) }
	val canScroll = remember { mutableStateOf(true) }
	
	// Monitor scroll changes using LaunchedEffect
	val currentIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
	val currentOffset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
	
	LaunchedEffect(currentIndex, currentOffset) {
		if (currentIndex == previousIndex.value && currentOffset > previousOffset.value) {
			// The user is scrolling down the list to the bottom:
			val nextItemIndex = listState.firstVisibleItemIndex + 1
			listState.scrollToItem(nextItemIndex)
			
		} else if (currentIndex == previousIndex.value && currentOffset < previousOffset.intValue) {
			// The user is scrolling up the list to the top:
			val previousItemIndex = listState.firstVisibleItemIndex
			if (previousItemIndex >= 0) {
				listState.scrollToItem(previousItemIndex)
			}
		}
		
		// Update the previous scroll state for the next check
		canScroll.value = false
		previousIndex.value = currentIndex
		previousOffset.value = currentOffset
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
			val content = list[index]
			content?.let {
				ContentUi(
					it,
					Modifier.fillParentMaxSize(),
					onLikeButtonPressed,
					onCommentButtonPressed
				)
			}
		}
	}
}