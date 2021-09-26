package github.io.wottrich.ui.components

import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import github.io.wottrich.ui.values.colorPrimary
import github.io.wottrich.ui.values.onPrimary

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun TopBarComponent(
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit,
    navigationIconAction: () -> Unit
) {
    TopAppBar(
        title = {
            ProvideTextStyle(MaterialTheme.typography.h6.copy(color = onPrimary)) {
                title()
            }
        },
        navigationIcon = {
            IconButton(onClick = { navigationIconAction() }) {
                navigationIcon()
            }
        },
        backgroundColor = colorPrimary
    )
}