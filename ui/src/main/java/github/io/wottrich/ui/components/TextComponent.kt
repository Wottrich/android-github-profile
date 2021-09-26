package github.io.wottrich.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun TitleRow(
    text: String,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier
) {
    BaseTextContent(
        modifier = contentModifier,
        textStyle = BaseTextStyle.TitleTextStyle,
        alpha = ContentAlpha.high
    ) {
        Text(
            modifier = modifier,
            text = text
        )
    }
}

@Composable
fun SubtitleRow(
    text: String,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier
) {
    BaseTextContent(
        modifier = modifier,
        textStyle = BaseTextStyle.SubtitleTextStyle,
        alpha = ContentAlpha.medium
    ) {
        Text(
            modifier = contentModifier,
            text = text
        )
    }
}

@Composable
fun BaseTextContent(
    modifier: Modifier,
    textStyle: TextStyle,
    alpha: Float,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier
    ) {
        CompositionLocalProvider(LocalContentAlpha provides alpha) {
            ProvideTextStyle(textStyle) {
                content()
            }
        }
    }
}

object BaseTextStyle {
    val TitleTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.h6
    val SubtitleTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.subtitle1
}