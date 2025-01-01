package com.bellogatecaliphate.quickreels.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bellogatecaliphate.core.model.routes.Route
import com.bellogatecaliphate.core.model.routes.account.AccountNavGraphRoute
import com.bellogatecaliphate.core.model.routes.create_post.CreatePostNavGraphRoute
import com.bellogatecaliphate.core.model.routes.timeline.TimelineNavGraphRoute
import com.bellogatecaliphate.quickreels.R

sealed class Screen(
	val route: Route,
	@StringRes val title: Int,
	@DrawableRes val imageId: Int
) {
	data object Timeline : Screen(TimelineNavGraphRoute, R.string.home, R.drawable.code)
	data object CreatePost : Screen(CreatePostNavGraphRoute, R.string.post, R.drawable.code)
	data object Chat : Screen(CreatePostNavGraphRoute, R.string.chat, R.drawable.code)
	data object Account : Screen(AccountNavGraphRoute, R.string.account, R.drawable.code)
}