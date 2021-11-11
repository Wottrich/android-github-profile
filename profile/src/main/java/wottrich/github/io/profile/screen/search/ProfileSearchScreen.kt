package wottrich.github.io.profile.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.ui.values.Roboto
import github.io.wottrich.ui.widgets.CircularProgress
import org.koin.androidx.compose.getViewModel
import wottrich.github.io.base.state.ScreenStateComponent
import wottrich.github.io.profile.ProfileSearchViewModel
import wottrich.github.io.profile.R
import wottrich.github.io.profilerepositorysharedcomponents.BuildProfileImage

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/11/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun ProfileSearchScreen(
    profileLoginQuery: String,
    onProfileSelected: (Profile) -> Unit,
    viewModel: ProfileSearchViewModel = getViewModel()
) {

    LaunchedEffect(
        key1 = profileLoginQuery,
        block = {
            viewModel.onQueryChange(profileLoginQuery)
        }
    )

    val state by viewModel.screenState.collectAsState()
    ScreenStateComponent(
        state = state,
        initial = {
            if (it.loading) {
                CircularProgress()
            }
        },
        failure = {},
        success = {
            if (it.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Nenhuma pesquisa recente...")
                }
            } else {
                LazyColumn(
                    content = {
                        items(it) { item ->
                            val cardModifier = Modifier
                                .padding(horizontal = 10.dp)
                                .padding(top = 10.dp)
                                .fillMaxWidth()

                            Card(modifier = cardModifier) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onProfileSelected(item) }
                                        .padding(8.dp)
                                ) {
                                    BuildProfileImage(avatarUrl = item.avatarUrl, imageSize = 32.dp)
                                    Column(
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    ) {
                                        Text(
                                            text = item.login,
                                            fontFamily = Roboto,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = item.name, fontFamily = Roboto)
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        IconButton(onClick = { viewModel.onDelete(item) }) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Deletar ussário pesquisado!"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    )

}