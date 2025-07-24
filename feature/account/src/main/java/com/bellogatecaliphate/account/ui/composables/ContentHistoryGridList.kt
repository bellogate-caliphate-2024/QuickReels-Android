package com.bellogatecaliphate.account.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.util.ContentPreviewParameter
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.util.PLACEHOLDER_4DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_CONTENT_HISTORY_HEIGHT
import com.bellogatecaliphate.core.util.PLACEHOLDER_CONTENT_HISTORY_WIDTH

@Composable
internal fun ContentHistoryGridList(
	list: LazyPagingItems<Content>,
	onOpenContent: (contentId: Content) -> Unit
) {
	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Fixed(2),
		modifier = Modifier
			.fillMaxSize()
			.padding(8.dp),
		contentPadding = PaddingValues(8.dp),
		verticalItemSpacing = 8.dp,
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		items(list.itemCount) { index ->
			val content = list[index]
			content?.let {
				Content(content = it, onOpenContent = onOpenContent)
			}
		}
	}
}

@Composable
@Preview(showBackground = true)
private fun Content(
	@PreviewParameter(ContentPreviewParameter::class) content: Content,
	onOpenContent: (content: Content) -> Unit = {}
) {
	Box(
		modifier = Modifier
			.padding(start = PLACEHOLDER_4DP, end = PLACEHOLDER_4DP)
			.size(PLACEHOLDER_CONTENT_HISTORY_WIDTH, PLACEHOLDER_CONTENT_HISTORY_HEIGHT)
			.aspectRatio(1f)
			.clickable { onOpenContent(content) },
		contentAlignment = Alignment.Center
	) {
		AsyncImage(
			model = content.thumbnailUrl,
			contentDescription = "content description",
			modifier = Modifier
				.fillMaxSize()
				.clip(RoundedCornerShape(16.dp))
		)
		Column {
			Image(painter = painterResource(id = R.drawable.eye), contentDescription = "")
			Text(text = content.numberOfViews, color = Color.White)
		}
	}
}