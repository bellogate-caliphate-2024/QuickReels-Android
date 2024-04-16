package com.bellogatecaliphate.quickreels.model

import androidx.annotation.StringRes
import com.bellogatecaliphate.quickreels.R

sealed class Screen(val route: String, @StringRes val title: Int) {
    data object Temp: Screen(Route.TEMP.name, (R.string.home))
    data object Home: Screen(Route.HOME.name, (R.string.home))
    data object Post: Screen(Route.POST.name, R.string.post)
    data object Chat: Screen(Route.CHAT.name, R.string.chat)
    data object Account: Screen(Route.ACCOUNT.name, R.string.account)
}