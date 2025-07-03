package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar

@Composable
internal fun LoadingScreen(show: Boolean) {
	if (show.not()) return
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		QuickReelsCircularProgressBar(show = show)
	}
}