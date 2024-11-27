package com.bellogatecaliphate.timeline.ui.timeline.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Content
import kotlinx.coroutines.launch

@Composable
fun Contents(list: LazyPagingItems<Content>?) {
	if (list == null) return
	val listState = rememberLazyListState()
	val coroutineScope = rememberCoroutineScope()
	
	LaunchedEffect(listState) {
		snapshotFlow { listState.isScrollInProgress }
			.collect { isScrolling ->
				if (! isScrolling) {
					// Snaps to the closest item when scrolling stops
					val itemIndex = listState.firstVisibleItemIndex
					val itemOffset = listState.firstVisibleItemScrollOffset
					
					coroutineScope.launch {
						// Snap to the next item if scrolled more than halfway
						if (itemOffset > 0 && itemOffset > listState.layoutInfo.viewportSize.height / 2) {
							listState.animateScrollToItem(itemIndex + 1)
						} else {
							listState.animateScrollToItem(itemIndex)
						}
					}
				}
			}
	}
	LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
		items(list.itemCount) { index ->
			val content = list[index]
			content?.let { ContentUi(it, Modifier.fillParentMaxSize()) }
		}
	}
}