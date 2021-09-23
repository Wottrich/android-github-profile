package wottrich.github.io.githubprofile.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.compose.foundation.ExperimentalFoundationApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.showAlert
import wottrich.github.io.githubprofile.ui.GithubApplicationTheme
import wottrich.github.io.githubprofile.view.widgets.ProfileScreen
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel

@ExperimentalFoundationApi
class ProfileActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubApplicationTheme {
                ProfileScreen(viewModel = viewModel)
            }
        }

        setupObserves()
    }

    private fun setupObserves() {
        val activity = this
        viewModel.apply {

            error.observe(activity) {
                showAlert(
                    getString(R.string.dialog_default_error_title),
                    if (it == null) getString(R.string.unknown_error)
                    else getString(it)
                ) {
                    setNeutralButton("OK", null)
                }
            }
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