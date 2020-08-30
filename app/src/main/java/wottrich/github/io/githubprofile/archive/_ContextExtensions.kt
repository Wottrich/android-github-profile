package wottrich.github.io.githubprofile.archive

import android.app.AlertDialog
import android.content.Context

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */


fun Context.showAlert (title: String, message: String, setup: (AlertDialog.Builder.() -> Unit)? = null) {
    val alert = AlertDialog.Builder(this)
    alert.setTitle(title)
    alert.setMessage(message)
    setup?.invoke(alert)
    alert.show()
}