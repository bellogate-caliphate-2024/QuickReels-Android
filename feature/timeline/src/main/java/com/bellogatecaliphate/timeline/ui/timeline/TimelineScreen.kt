package com.bellogatecaliphate.timeline.ui.timeline

//07019996936

import androidx.compose.runtime.Composable
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.timeline.ui.timeline.content.Contents

@Composable
fun TimeLineScreen() {

}

@Composable
private fun TimeLineScreen(list: List<Content>) {
	Contents(list)
}