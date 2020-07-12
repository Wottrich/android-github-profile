package wottrich.github.io.githubprofile.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.hideKeyboard
import wottrich.github.io.githubprofile.archive.showAlertWithOkButton
import wottrich.github.io.githubprofile.archive.showAlertWithTryAgainButton
import wottrich.github.io.githubprofile.data.datasource.Services
import wottrich.github.io.githubprofile.view.adapter.RepositoryAdapter
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel


class ProfileActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setupObserves()
        setupRecyclerView()

    }

    private fun setupRecyclerView () {
        val activity = this

        rvRepositories.apply {
            adapter = RepositoryAdapter(
                activity,
                activity.viewModel.repositories
            )
        }

    }

    private fun setupObserves () {
        val activity = this

        viewModel.apply {

            repositories.observe(activity, Observer {
                rvRepositories.adapter?.notifyDataSetChanged()
            })

            profile.observe(activity, Observer {
                if (it != null) {
                    clProfile.isVisible = true

                    tvName.text = it.name
                    tvBio.text = it.bio
                    tvLogin.text = String.format(getString(R.string.format_profile_login), it.login)
                    tvFollowers.text = String.format(
                        getString(R.string.format_profile_followers, it.followers.toString())
                    )
                    tvFollowing.text = String.format(
                        getString(R.string.format_profile_following, it.following.toString())
                    )

                    imgProfile.isVisible = true

                    //Set Image
                    Glide.with(activity)
                        .load(it.avatarUrl)
                        .centerCrop()
                        .placeholder(R.drawable.ic_person_32)
                        .into(imgProfile)

                }
            })

            loadingProfile.observe(activity, Observer {
                profileLoading()
                val loading = it ?: false
                progressBarProfile.visibility = if (loading) View.VISIBLE else View.GONE
            })

            loadingRepository.observe(activity, Observer {
                val loading = it ?: false
                progressBarRepositories.visibility = if (loading) View.VISIBLE else View.GONE
            })

            error.observe(activity, Observer {
                if (it != null) {

                    val defaultMessage =
                        if(it.service === Services.profile) getString(R.string.error_profile_unknown_error)
                        else getString(R.string.error_repositories_unknown_error)
                    val message = it.message ?: defaultMessage

                    if (it.service === Services.profile) {
                        profileError()
                        tvProfileError.text = message
                    } else if (it.service === Services.repositories) {
                        repositoryError()
                        tvRepositoriesError.text = message
                    }

                }
            })
        }
    }

    private fun profileLoading () {
        clProfile.isVisible = false
        tvWelcomeFindProfile.isVisible = false
        tvProfileError.isVisible = false
        tvRepositoriesError.isVisible = false
    }

    private fun profileError () {
        clProfile.isVisible = false
        tvProfileError.isVisible = true
    }

    private fun repositoryError () {
        tvRepositoriesError.isVisible = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)

        val searchItem = menu?.findItem(R.id.itFilter)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem?.actionView as? SearchView

        searchItem?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean = true

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                return true
            }
        })

        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(this@ProfileActivity)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            viewModel.loadServices(query)
            return false
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

}