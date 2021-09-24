package github.io.wottrich.datasource.dispatchers

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 16/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
data class AppDispatchers(
    val main: CoroutineContext = Dispatchers.Main,
    val io: CoroutineContext = Dispatchers.IO
)