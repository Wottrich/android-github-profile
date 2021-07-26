package wottrich.github.io.githubprofile.ui.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 03/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@Composable
fun TextView(
    text: String?,
    modifier: Modifier = Modifier,
    textNull: String = "",
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    isVisible: Boolean = true,
    style: TextStyle = TextStyle.Default
) {
    if (isVisible) {
        Text(
            text = text ?: textNull,
            color = color,
            modifier = modifier,
            textAlign = textAlign,
            style = style
        )
    }
}