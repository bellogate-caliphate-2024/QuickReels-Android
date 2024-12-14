package com.bellogatecaliphate.account.ui.content_history_grid_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.util.ContentPreviewParameter
import com.bellogatecaliphate.core.util.PLACEHOLDER_4DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_CONTENT_HISTORY_HEIGHT
import com.bellogatecaliphate.core.util.PLACEHOLDER_CONTENT_HISTORY_WIDTH
import com.bellogatecaliphate.core.model.dto.Content

@Composable
fun ContentHistoryGridList(list: LazyPagingItems<Content>) {
	LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
		items(list.itemCount) { index ->
			val content = list[index]
			content?.let { Content(it) }
		}
	}
}

@Composable
@Preview(showBackground = true)
private fun Content(@PreviewParameter(ContentPreviewParameter::class) content: Content) {
	Box(
		modifier = Modifier
			.padding(start = PLACEHOLDER_4DP, end = PLACEHOLDER_4DP)
			.size(PLACEHOLDER_CONTENT_HISTORY_WIDTH, PLACEHOLDER_CONTENT_HISTORY_HEIGHT)
			.aspectRatio(1f),
		contentAlignment = Alignment.Center
	) {
		/*AsyncImage(
				model = content.thumbnailUrl,
				contentDescription = "content description",
				modifier = Modifier
				.fillMaxSize()
				.clip(RoundedCornerShape(16.dp))
			)*/
		Image(
			painter = painterResource(id = R.drawable.ic_launcher_background),
			contentDescription = "content description",
			modifier = Modifier
				.fillMaxSize()
				.clip(RoundedCornerShape(4.dp))
		)
		Column {
			Image(painter = painterResource(id = R.drawable.eye), contentDescription = "")
			Text(text = content.numberOfViews, color = Color.White)
		}
	}
}