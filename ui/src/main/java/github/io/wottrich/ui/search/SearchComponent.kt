package github.io.wottrich.ui.search

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import github.io.wottrich.ui.R
import github.io.wottrich.ui.values.colorPrimary
import github.io.wottrich.ui.values.githubApplicationTypography
import github.io.wottrich.ui.values.onPrimary

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 24/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

enum class SearchState {
    Focused,
    InitialState
}

@Composable
fun SearchComponent(
    focusRequester: FocusRequester = remember { FocusRequester() },
    onValueChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit,
    onSearchStateChanged: (SearchState) -> Unit,
    searchState: SearchState,
    textFieldValue: TextFieldValue
) {
    val animationDpAsState by updateTransition(searchState, label = "SearchTransitionState")
        .animateDp(label = "DpTransition") {
            when (it) {
                SearchState.Focused -> SearchDefaults.searchFocusedPadding
                SearchState.InitialState -> SearchDefaults.searchInitialStatePadding
            }
        }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(SearchDefaults.componentHeight)
            .padding(all = animationDpAsState),
        color = colorPrimary,
        shape = RoundedCornerShape(animationDpAsState),
        elevation = SearchDefaults.searchSurfaceElevation
    ) {
        Crossfade(targetState = searchState) { state ->
            when (state) {
                SearchState.Focused ->
                    SearchField(
                        focusRequester = focusRequester,
                        searchState = searchState,
                        textFieldValue = textFieldValue,
                        onValueChange = {
                            if (textFieldValue != it) {
                                onValueChange(it)
                            }
                        },
                        onSearch = {
                            onSearchStateChanged(SearchState.InitialState)
                            onSearch()
                        },
                        onClearValue = {
                            if (it.text.isEmpty()) {
                                onSearchStateChanged(SearchState.InitialState)
                            }
                            onValueChange(TextFieldValue())
                        }
                    )
                SearchState.InitialState -> SearchContent(
                    onSearchStateChanged = onSearchStateChanged,
                    query = textFieldValue.text
                )
            }
        }
    }
}

@Composable
private fun SearchContent(onSearchStateChanged: (SearchState) -> Unit, query: String) {
    val pageTitle = if (query.isEmpty()) stringResource(id = R.string.app_name) else query
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SearchDefaults.searchContentHeight)
            .padding(horizontal = 8.dp)
            .clickable(onClick = { onSearchStateChanged(SearchState.Focused) }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = pageTitle,
            style = githubApplicationTypography.h6,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = onPrimary
        )
        Icon(
            modifier = Modifier.weight(1f, fill = false),
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(id = R.string.start_search_content_description),
            tint = onPrimary
        )
    }
}

@Composable
private fun SearchField(
    onValueChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit,
    onClearValue: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester,
    searchState: SearchState,
    textFieldValue: TextFieldValue
) {
    TextField(
        modifier = Modifier
            .fillMaxSize()
            .focusRequester(focusRequester),
        value = textFieldValue,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        trailingIcon = {
            IconClear {
                onClearValue(textFieldValue)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = onPrimary,
            cursorColor = onPrimary,
            focusedIndicatorColor = Color.Transparent
        )
    )

    SideEffect {
        if (searchState == SearchState.Focused) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
private fun IconClear(onClearValue: () -> Unit) {
    IconButton(onClick = { onClearValue() }) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = stringResource(id = R.string.clear_search_content_description),
            tint = onPrimary
        )
    }
}

private object SearchDefaults {
    val componentHeight = 56.dp
    val searchContentHeight = 56.dp
    val searchFocusedPadding = 0.dp
    val searchInitialStatePadding = 8.dp
    val searchSurfaceElevation = 4.dp
}