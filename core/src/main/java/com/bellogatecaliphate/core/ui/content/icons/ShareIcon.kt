package com.bellogatecaliphate.core.ui.content.icons

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP

@Composable
internal fun ShareIcon(textToShare: String) {
	val context = LocalContext.current
	Image(
		modifier = Modifier
			.size(PLACEHOLDER_24DP)
			.clickable {
				shareUrl(context = context, text = textToShare)
			},
		painter = painterResource(id = R.drawable.icon_next),
		contentDescription = ""
	)
}

private fun shareUrl(context: Context, text: String) {
	val sendIntent = Intent().apply {
		action = Intent.ACTION_SEND
		putExtra(Intent.EXTRA_TEXT, text)
		type = "text/plain"
	}
	
	val shareIntent = Intent.createChooser(sendIntent, null)
	context.startActivity(shareIntent)
}