package wottrich.github.io.githubprofile.view.screen

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.luca992.compose.image.CoilImage
import wottrich.github.io.githubprofile.data.resource.Status
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 02/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val profileState = viewModel.profileResult.observeAsState(null)

    Column {
        when (profileState.value?.status) {
            Status.SUCCESS -> {
                val profile = profileState.value?.data
                CoilImage(profile?.avatarUrl ?: "")
                Text(text = profile?.name ?: "Error")
            }
            Status.LOADING -> CircularProgressIndicator()
            Status.ERROR -> Unit
        }

        Button(onClick = { viewModel.loadServices("Wottrich") }) {
            Text(text = "Click here")
        }
    }





}