package wottrich.github.io.githubprofile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

abstract class BaseUnitTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

}