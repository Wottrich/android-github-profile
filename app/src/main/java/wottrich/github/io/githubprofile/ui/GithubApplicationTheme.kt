package wottrich.github.io.githubprofile.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import wottrich.github.io.githubprofile.ui.values.githubApplicationTypography

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 02/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

//title
//<item name="android:textSize">12sp</item>
//        <item name="android:textColor">@color/colorAccent</item>



@Composable
fun GithubApplicationTheme(content: @Composable() () -> Unit) {

    MaterialTheme (
        typography = githubApplicationTypography,
        content = content
    )

}