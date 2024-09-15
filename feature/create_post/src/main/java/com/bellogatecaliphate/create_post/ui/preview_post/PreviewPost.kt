package com.bellogatecaliphate.create_post.ui.preview_post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun PreviewPost(onSendButtonClicked : () -> Unit = {}) {
	Box(Modifier.fillMaxSize()) {
		VideoPreview()
		Column(modifier = Modifier.align(Alignment.BottomCenter)) {
			VideoDescriptionSection()
			SendButton(onSendButtonClicked)
		}
	}
}

@Composable
private fun VideoPreview() {
	
	Text(text = "Preview of button")
	
}

@Composable
private fun VideoDescriptionSection() {
	var text by rememberSaveable { mutableStateOf("") }
	OutlinedTextField(modifier = Modifier.fillMaxWidth() ,
		value = text ,
		onValueChange = { text = it } ,
		label = { Text("Add a caption...") })
}

@Composable
private fun SendButton(onClick : () -> Unit) {
	TextButton(onClick = onClick , modifier = Modifier.fillMaxWidth()) {
		Text("Send" , fontSize = 20.sp)
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewPostPreview() {
	PreviewPost()
}
