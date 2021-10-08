package wottrich.github.io.githubprofile.boundary

import android.app.Activity
import wottrich.github.io.repository.RepositoryActivity
import wottrich.github.io.profile.boundary.ProfileBoundary

class ProfileBoundaryImpl : ProfileBoundary {
    override fun launchRepositoryDetail(
        activity: Activity,
        profileLogin: String,
        repositoryName: String
    ) {
        RepositoryActivity.launch(activity, profileLogin, repositoryName)
    }
}