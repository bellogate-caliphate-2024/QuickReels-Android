package com.bellogatecaliphate.chat.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SelectUserScreen(viewmodel: SelectUserViewmodel = hiltViewModel()) {
	
	Button(onClick = {}) {
		Text(text = "Select User")
	}
}