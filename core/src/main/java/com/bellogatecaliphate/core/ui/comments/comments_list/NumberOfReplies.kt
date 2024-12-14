package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.ui.comments.model.Comment
import com.bellogatecaliphate.core.ui.comments.util.getNumberOdRepliesText

private const val INITIAL_PAGE_NUMBER = 1

/***
 * @isLoadingReplies: Boolean is true only when the app is attempting to fetch replies either for the first time or while fetch more.
 * @numberOfReliesLoaded: Int is the total number of replies that have been fetched and displayed.
 */

@Composable
internal fun NumberOfReplies(
	comment: Comment,
	isLoadingReplies: Boolean,
	numberOfRepliesLoaded: Int,
	onLoadReplies: (pageNumber: Int) -> Unit = {}
) {
	if (comment.hasReplies()) {
		Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
			val isLoadingRepliesForTheFirstTime = numberOfRepliesLoaded == 0
			if (isLoadingReplies && isLoadingRepliesForTheFirstTime) {
				CircularProgressIndicator()
			} else {
				Text(
					text = getNumberOdRepliesText(comment.numberOfReplies),
					Modifier.fillMaxWidth().clickable { onLoadReplies(INITIAL_PAGE_NUMBER) },
					textAlign = TextAlign.Center,
					color = colorResource(id = R.color.purple_500)
				)
			}
		}
	}
}