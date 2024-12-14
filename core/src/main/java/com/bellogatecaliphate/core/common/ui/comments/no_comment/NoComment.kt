package com.bellogatecaliphate.core.common.ui.comments.no_comment

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.bellogatecaliphate.core.R

@Composable
fun NoComment() {
	Box(contentAlignment = Alignment.Center) {
		Text(text = stringResource(id = R.string.no_comments))
	}
}