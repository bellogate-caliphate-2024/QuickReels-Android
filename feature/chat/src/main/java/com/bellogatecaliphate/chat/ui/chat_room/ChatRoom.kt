package com.bellogatecaliphate.chat.ui.chat_room

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ChatRoom(chatUserId: String) {
	Text(text = "Hello you are chatting with $chatUserId")
}

