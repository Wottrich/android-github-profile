package wottrich.github.io.githubprofile.ui.repository

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import github.io.wottrich.ui.GithubApplicationTheme
import github.io.wottrich.ui.components.TopBarComponent
import github.io.wottrich.ui.values.backgroundColor
import github.io.wottrich.ui.values.onPrimary
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.startActivity
import wottrich.github.io.githubprofile.ui.repository.navigation.RepositoryFlow
import wottrich.github.io.githubprofile.ui.repository.screen.detail.RepositoryScreen

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

class RepositoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            GithubApplicationTheme {
                Scaffold(
                    topBar = {
                        TopBarComponent(title = {
                            Text(text = stringResource(id = R.string.repository_title))
                        }, navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(
                                    id = R.string.arrow_back_content_description
                                ),
                                tint = onPrimary
                            )
                        }, navigationIconAction = ::onBackPressed)
                    },
                    backgroundColor = backgroundColor
                ) {
                    AppNavigator(navHostController)
                }
            }
        }
    }

    @Composable
    private fun AppNavigator(navHostController: NavHostController) {
        NavHost(
            navController = navHostController,
            startDestination = RepositoryFlow.RepositoryDetail.route,
            builder = {
                composable(RepositoryFlow.RepositoryDetail.route) {
                    RepositoryScreen(
                        getProfileLogin(),
                        getRepositoryName()
                    )
                }
            }
        )
    }

    private fun getProfileLogin() =
        intent.getStringExtra(KEY_PROFILE_LOGIN).orEmpty()

    private fun getRepositoryName() =
        intent.getStringExtra(KEY_REPOSITORY_NAME).orEmpty()

    companion object {
        private const val KEY_PROFILE_LOGIN = "KEY_PROFILE_LOGIN"
        private const val KEY_REPOSITORY_NAME = "KEY_REPOSITORY_NAME"

        fun launch(activity: Activity, profileLogin: String, repositoryName: String) {
            activity.startActivity<RepositoryActivity> {
                putExtra(KEY_PROFILE_LOGIN, profileLogin)
                putExtra(KEY_REPOSITORY_NAME, repositoryName)
            }
        }
    }

}