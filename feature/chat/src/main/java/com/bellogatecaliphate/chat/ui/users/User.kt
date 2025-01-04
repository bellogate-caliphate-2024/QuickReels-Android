package com.bellogatecaliphate.chat.ui.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_IMAGE_40DP

@Composable
internal fun User(chatUser: User, onUserSelected: (chatUser: User) -> Unit) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.clickable { onUserSelected(chatUser) }
			.fillMaxWidth()
			.padding(PLACEHOLDER_8DP)
	) {
		/*AsyncImage(
			model = "https://example.com/image.jpg",
			contentDescription = null,
			modifier = Modifier
				.size(PLACEHOLDER_IMAGE_40DP)
				.clip(CircleShape)
		)*/
		Image(
			painter = painterResource(id = com.bellogatecaliphate.core.R.drawable.ic_launcher_background),
			contentDescription = "content description",
			modifier = Modifier
				.size(PLACEHOLDER_IMAGE_40DP)
				.clip(CircleShape)
		)
		Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
		Text(text = chatUser.accountName)
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewUser() {
	val chatUser = User("", "Jeff Emuveyan", "", "", "")
	User(chatUser = chatUser) {}
}