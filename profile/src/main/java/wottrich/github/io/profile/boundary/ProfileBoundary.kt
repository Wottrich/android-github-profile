package wottrich.github.io.profile.boundary

import android.app.Activity

interface ProfileBoundary {
    fun launchRepositoryDetail(
        activity: Activity,
        profileLogin: String,
        repositoryName: String
    )
}