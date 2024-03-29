package wottrich.github.io.repository.screen.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.ui.components.SubtitleRow
import wottrich.github.io.profilerepositorysharedcomponents.BuildProfileImage
import wottrich.github.io.repository.R

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun RepositoryOwner(owner: Profile) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = R.string.repository_owner_label))
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BuildProfileImage(avatarUrl = owner.avatarUrl, imageSize = 32.dp)
            SubtitleRow(
                modifier = Modifier.padding(start = 8.dp),
                text = owner.login
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}