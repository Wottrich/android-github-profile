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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.showAlertWithOkButton
import wottrich.github.io.githubprofile.databinding.ActivityProfileBinding
import wottrich.github.io.githubprofile.view.adapter.RepositoryAdapter
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel

class ProfileActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        setupBinding()
        setupObserves()
        setupRecyclerView()

    }

    private fun setupBinding () {
        val activity = this
        binding.apply {
            lifecycleOwner = activity
            viewModel = activity.viewModel
        }
    }

    private fun setupRecyclerView () {
        val activity = this

       binding.rvRepositories.apply {
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
                binding.rvRepositories.adapter?.notifyDataSetChanged()
            })

            profile.observe(activity, Observer {
                if (it != null) {
                    binding.apply {

                        clProfile.isVisible = true
                        imgProfile.isVisible = true

                        //Set Image
                        Glide.with(activity)
                            .load(it.avatarUrl)
                            .centerCrop()
                            .placeholder(R.drawable.ic_person_32)
                            .into(imgProfile)

                    }
                } else {
                    binding.clProfile.isVisible = false
                    binding.imgProfile.isVisible = false
                }
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
            viewModel.loadServices(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

}