package wottrich.github.io.githubprofile

import github.io.wottrich.datasource.dispatchers.AppDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

class CoroutinesTestRule(
    val testDispatchers: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    val dispatchers = AppDispatchers(testDispatchers, testDispatchers)

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatchers)
    }

    @ExperimentalCoroutinesApi
    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatchers.cleanupTestCoroutines()
    }

}