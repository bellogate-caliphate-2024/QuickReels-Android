package com.bellogatecaliphate.chat.ui.users

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.User

@Composable
internal fun UsersList(users: LazyPagingItems<User>?, onUserSelected: (chatUser: User) -> Unit) {
	if (users == null) return
	val listState = rememberLazyListState()
	
	LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
		items(users.itemCount) { index ->
			val user = users[index]
			user?.let { User(user, onUserSelected) }
		}
	}
}