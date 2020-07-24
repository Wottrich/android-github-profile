package wottrich.github.io.githubprofile.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.showAlertWithOkButton
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
                    imgProfile.isVisible = true

                    tvName.text = it.name
                    tvBio.text = it.bio
                    tvLogin.text = String.format(getString(R.string.format_profile_login), it.login)
                    tvFollowers.text = String.format(
                        getString(R.string.format_profile_followers, it.followers.toString())
                    )
                    tvFollowing.text = String.format(
                        getString(R.string.format_profile_following, it.following.toString())
                    )

                    //Set Image
                    Glide.with(activity)
                        .load(it.avatarUrl)
                        .centerCrop()
                        .placeholder(R.drawable.ic_person_32)
                        .into(imgProfile)

                } else {
                    clProfile.isVisible = false
                    imgProfile.isVisible = false
                }
            })

            loadingProfile.observe(activity, Observer {
                val loading = it ?: false
                progressBarProfile.isVisible = loading
            })

            loadingRepository.observe(activity, Observer {
                val loading = it ?: false
                progressBarRepositories.isVisible = loading
            })

            profileError.observe(activity, Observer {
                tvProfileError.isVisible = it != null
                tvProfileError.text = it
            })

            repositoriesError.observe(activity, Observer {
                tvRepositoriesError.text = it
            })

            error.observe(activity, Observer {
                showAlertWithOkButton(
                    getString(R.string.dialog_default_error_title),
                    if(it == null) getString(R.string.unknown_error)
                    else getString(it)
                )
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)

        val searchItem = menu?.findItem(R.id.itFilter)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem?.actionView as? SearchView

        searchItem?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean = true

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean = true
        })

        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(this@ProfileActivity)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            tvWelcomeFindProfile.isVisible = false
            viewModel.loadServices(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

}