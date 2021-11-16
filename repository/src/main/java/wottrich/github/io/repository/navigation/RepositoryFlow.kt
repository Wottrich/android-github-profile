package wottrich.github.io.repository.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import wottrich.github.io.base.navigation.BaseNavigationModelImpl

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

sealed class RepositoryFlow {

    object RepositoryDetail : BaseNavigationModelImpl(
        route = suffixRepositoryFlow("detail")
    )

    object RepositoryArchives : BaseNavigationModelImpl(
        route = suffixRepositoryFlow("detail/archives"),
        arguments = listOf(
            navArgument("path") { type = NavType.StringType }
        )
    ) {
        val argument = "path"

        override val routeWithArgument: String
            get() = "$route/{$argument}"

        fun route(path: String): String {
            val replaceSlashToVerticalLine = path.replace("/", "|")
            return "$route/$replaceSlashToVerticalLine"
        }

        fun getArgumentValue(navBackStackEntry: NavBackStackEntry): String {
            val path = navBackStackEntry.arguments?.getString(argument).toString()
            return path.replace("|", "/")
        }


    }

    companion object {
        private fun suffixRepositoryFlow(route: String) = "repository/$route"
    }

}