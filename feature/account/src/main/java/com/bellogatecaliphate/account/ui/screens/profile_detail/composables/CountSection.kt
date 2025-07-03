package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun CountSectionList(
	numberOfFollowers: String,
	numberOfFollowing: String,
	numberOfViews: String
) {
	Row {
		CountSection(stringResource(R.string.followers), numberOfFollowers)
		CountSection(stringResource(R.string.following), numberOfFollowing)
		CountSection(stringResource(R.string.views), numberOfViews)
	}
}

@Composable
private fun CountSection(title: String, count: String) {
	Column(
		modifier = Modifier.padding(PLACEHOLDER_16DP),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			fontWeight = FontWeight.Bold,
			text = title
		)
		Text(count)
	}
}