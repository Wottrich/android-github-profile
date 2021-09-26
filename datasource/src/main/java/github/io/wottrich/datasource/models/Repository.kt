package github.io.wottrich.datasource.models

import com.google.gson.annotations.SerializedName
import github.io.wottrich.datasource.colorLanguages

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

data class Repository(
    val name: String,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    val description: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val language: String?,
    val fork: Boolean?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    val watchers: Int,
    @SerializedName("open_issues_count")
    val openPullRequestCount: Int,
    val owner: Profile
) {

    val languageColor: String
        get() {
            val color = "#a3a3a3"
            return if (colorLanguages?.containsKey(language) == true) {
                colorLanguages[language] ?: color
            } else {
                color
            }
        }

}