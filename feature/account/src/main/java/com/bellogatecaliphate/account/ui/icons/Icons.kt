package com.bellogatecaliphate.account.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.common.util.PLACEHOLDER_8DP

@Composable
internal fun Likes(numberOfLikes: String) {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		Image(painter = painterResource(id = R.drawable.icon_grey_heart), contentDescription = "")
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Text(text = numberOfLikes)
		Text(text = "Likes", color = colorResource(id = R.color.ash))
	}
}

@Composable
internal fun Views(numberOfViews: String) {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		Image(painter = painterResource(id = R.drawable.eye), contentDescription = "")
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Text(text = numberOfViews)
		Text(text = "Views", color = colorResource(id = R.color.ash))
	}
}