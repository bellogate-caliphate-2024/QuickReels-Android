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
	@DrawableRes val selectedImageId: Int,
	@DrawableRes val unSelectedImageId: Int,
) {
	data object Timeline :
			Screen(
				TimelineNavGraphRoute,
				R.string.home,
				R.drawable.menu_item_home_selected,
				R.drawable.menu_item_home_unselected
			)
	
	data object CreatePost : Screen(
		CreatePostNavGraphRoute,
		R.string.post,
		R.drawable.menu_item_create_post_selected,
		R.drawable.menu_item_create_post_unselected
	)
	
	data object Account :
			Screen(AccountNavGraphRoute, R.string.account, R.drawable.code, R.drawable.code)
}