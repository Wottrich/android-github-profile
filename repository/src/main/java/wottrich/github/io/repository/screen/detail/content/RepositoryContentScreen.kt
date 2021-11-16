package wottrich.github.io.repository.screen.detail.content

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import github.io.wottrich.ui.widgets.CircularProgress
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import wottrich.github.io.repository.screen.detail.components.repositoryContents

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 15/11/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun RepositoryContentScreen(
    profileLogin: String,
    repositoryName: String,
    path: String,
    viewModel: RepositoryContentViewModel = getViewModel {
        parametersOf(profileLogin, repositoryName, path)
    },
    onContentClick: (String) -> Unit
) {

    val contentState by viewModel.contentState.collectAsState()
    LazyColumn(
        content = {
            repositoryContents(
                contentsState = contentState,
                onInitialContent = {
                    if (it.loading) {
                        CircularProgress()
                    }
                },
                onContentClick = onContentClick
            )
        }
    )
}