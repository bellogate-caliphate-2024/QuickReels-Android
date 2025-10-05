package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.ui.comments.util.getNumberOdRepliesText

private const val INITIAL_PAGE_NUMBER = 1

/***
 * @isLoadingReplies: Boolean is true only when the app is attempting to fetch replies either for the first time or while fetch more.
 * @numberOfReliesLoaded: Int is the total number of replies that have been fetched and displayed.
 * @isFirstReply: is true when the first reply to a comment has taken place.
 */

@Composable
internal fun NumberOfReplies(
	visible: Boolean,
	commentId: String,
	isFirstReply: Boolean,
	numberOfReplies: Int?,
	isLoadingReplies: Boolean,
	numberOfRepliesLoaded: Int,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> }
) {
	if (visible) {
		Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
			val isLoadingRepliesForTheFirstTime = numberOfRepliesLoaded == 0
			if (isLoadingReplies && isLoadingRepliesForTheFirstTime) {
				QuickReelsCircularProgressBar()
			} else {
				Text(
					text = getNumberOdRepliesText(if (isFirstReply) 1 else numberOfReplies),
					Modifier
						.fillMaxWidth()
						.clickable { onLoadReplies(commentId, INITIAL_PAGE_NUMBER) },
					textAlign = TextAlign.Center,
					color = colorResource(id = R.color.quickreels_purple),
					style = MaterialTheme.typography.bodySmall,
					fontWeight = FontWeight.Bold
				)
			}
		}
	}
}