package wottrich.github.io.githubprofile.ui.repository.screen.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import github.io.wottrich.ui.components.SubtitleRow
import github.io.wottrich.ui.components.TitleRow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun RepositoryInformation(name: String, description: String?) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        TitleRow(text = name)
        description?.let {
            SubtitleRow(
                modifier = Modifier.padding(top = 8.dp),
                text = it
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}