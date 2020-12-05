package wottrich.github.io.githubprofile.ui.widgets

import androidx.compose.foundation.Text
import androidx.compose.foundation.currentTextStyle
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
    textNull: String = "",
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    modifier: Modifier = Modifier,
    isVisible: Boolean? = true,
    style: TextStyle = currentTextStyle()
) {
    if (isVisible == false) {
        return
    }

    return Text(
        text = text ?: textNull,
        color = color,
        modifier = modifier,
        textAlign = textAlign,
        style = style
    )
}