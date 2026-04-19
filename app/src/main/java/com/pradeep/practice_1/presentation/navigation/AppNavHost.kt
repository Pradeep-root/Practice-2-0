package com.pradeep.practice_1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pradeep.practice_1.presentation.posts.PostsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Posts.route,
        modifier = modifier
    ) {
        composable(AppRoute.Posts.route) {
            PostsScreen()
        }
    }
}