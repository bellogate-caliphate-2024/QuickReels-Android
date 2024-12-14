package com.bellogatecaliphate.core.common.ui.comments.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bellogatecaliphate.core.R

@Composable
fun getNumberOdRepliesText(numberOfReplies: Int?): String {
	if (numberOfReplies == null || numberOfReplies == 0) return ""
	if (numberOfReplies == 1) return "1 ${stringResource(id = R.string.reply)}"
	return "$numberOfReplies ${stringResource(id = R.string.replies)}"
}

@Composable
fun getCommentsListHeaderText(numberOfComments: Int): String {
	if (numberOfComments == 0) return ""
	if (numberOfComments == 1) return "1 ${stringResource(id = R.string.comment)}"
	return "$numberOfComments ${stringResource(id = R.string.comments)}"
}