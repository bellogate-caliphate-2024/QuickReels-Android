package com.bellogatecaliphate.quickreels.model

import androidx.annotation.DrawableRes
import com.bellogatecaliphate.quickreels.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Route(@DrawableRes val icon: Int) {
    @Serializable data object Temporary: Route(R.drawable.ic_launcher_foreground)
    @Serializable data object Reels: Route(R.drawable.ic_launcher_foreground)
    @Serializable data object CreatePost: Route(R.drawable.ic_launcher_foreground)
    @Serializable data object Account: Route(R.drawable.ic_launcher_foreground)
}
