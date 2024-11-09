package com.bellogatecaliphate.timeline.ui.timeline.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.timeline.R
import com.bellogatecaliphate.timeline.ui.timeline.icons.CommentsIcon
import com.bellogatecaliphate.timeline.ui.timeline.icons.LikeIcon
import com.bellogatecaliphate.timeline.util.ContentPreviewParameter
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_ICON_SIZE
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_IMAGE_40DP

@Composable
@Preview(showBackground = true)
internal fun Content(
	@PreviewParameter(ContentPreviewParameter::class) content: Content,
	modifier: Modifier = Modifier
) {
	Column(modifier) {
		UserDetailsSection(content.userProfilePicture, content.userName, content.date)
		VideoSection(Modifier.weight(1f), content.videoUrl)
		BottomSection(content.caption, content.numberOfLikes, content.numberOfComments)
	}
}

@Composable
private fun UserDetailsSection(userProfilePicture: String, userName: String, date: String) {
	Column(
		Modifier
			.fillMaxWidth()
			.padding(PLACEHOLDER_8DP)
	) {
		Row {
			/*
			SINCE WE ARE STILL CODING, WE WILL COMMENT OUT AsyncImage, AND REPLACE IT WITH Image.
			LATER, WE WILL FINISH REMOVE THE Image.
			AsyncImage(
				model = "https://example.com/image.jpg",
				contentDescription = null,
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)*/
			Image(
				painter = painterResource(id = R.drawable.placeholder_image),
				contentDescription = "content description",
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Column {
				Text(text = userName)
				Text(text = date)
			}
		}
	}
}

@Composable
private fun VideoSection(modifier: Modifier, videoUrl: String) {
	Column(modifier.background(colorResource(id = com.bellogatecaliphate.core.R.color.purple_500))) {
		Image(
			painter = painterResource(id = R.drawable.placeholder_image),
			contentDescription = "content description",
			modifier = Modifier.fillMaxSize()
		)
	}
}

@Composable
private fun BottomSection(
	caption: String,
	numberOfLikes: String,
	numberOfComments: String
) {
	Column(Modifier.padding(PLACEHOLDER_8DP)) {
		Row {
			LikeIcon(numberOfLikes) {}
			Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
			CommentsIcon(numberOfComments) {}
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Image(
				modifier = Modifier.size(PLACEHOLDER_ICON_SIZE),
				painter = painterResource(id = R.drawable.icon_next),
				contentDescription = ""
			)
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Text(text = caption)
	}
}