package wottrich.github.io.githubprofile.model

import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.data.datasource.Services

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

data class ErrorWrapper(
    val message: String?,
    val service: Services,
    val close: Boolean = false
)