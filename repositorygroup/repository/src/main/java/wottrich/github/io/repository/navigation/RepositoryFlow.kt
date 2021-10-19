package wottrich.github.io.repository.navigation

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
        route = suffixRepositoryFlow("detail/path")
    )

    companion object {
        private fun suffixRepositoryFlow(route: String) = "repository/$route"
    }

}