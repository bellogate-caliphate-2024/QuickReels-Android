package com.bellogatecaliphate.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProgressBar(show: Boolean) {
	if (show.not()) return
	LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
}