package com.pradeep.practice_1.presentation.navigation

sealed class AppRoute(
    val route: String
) {
    data object Posts: AppRoute("posts")
}