package com.bellogatecaliphate.create_post.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bellogatecaliphate.core.model.Route
import com.bellogatecaliphate.create_post.ui.CreatePostScreen
import com.bellogatecaliphate.create_post.ui.preview_post.PreviewPost

fun NavGraphBuilder.createPostNavGraph() {
    navigation<Route.CreatePostNavGraph>(startDestination = Route.CreatePost::class) {
        composable<Route.CreatePost> { CreatePostScreen() }
        composable<Route.PreviewPost> { PreviewPost() }
    }
}