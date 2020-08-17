package wottrich.github.io.githubprofile.archive

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Exception

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */


fun Activity.hideKeyboard() {
    try {
        val view = this.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    } catch (e: Exception) {
        e.printStackTrace()//Remove
    }
}

fun Context.showAlert (title: String, message: String, setup: (AlertDialog.Builder.() -> Unit)? = null) {
    val alert = AlertDialog.Builder(this)
    alert.setTitle(title)
    alert.setMessage(message)
    setup?.invoke(alert)
    alert.show()
}

// **********
// * ALERTS *
// **********
fun Context.showAlertWithOkButton(
    title: String, message: String, isError: Boolean = false, listener: (() -> Unit)? = null
) {

    val alert = AlertDialog.Builder(this)
    if (isError) {
        alert.setIcon(android.R.drawable.stat_sys_warning)
    }
    alert.setTitle(title)
    alert.setMessage(message)
    alert.setNeutralButton("OK") { _, _ -> listener?.invoke() }
    alert.show()

}

fun Context.showAlertWithTryAgainButton(
    title: String, message: String, isError: Boolean = false,
    tryAgainCallback: (() -> Unit)? = null, listener: (() -> Unit)? = null
) {

    val alert = AlertDialog.Builder(this)
    if (isError) {
        alert.setIcon(android.R.drawable.stat_sys_warning)
    }
    alert.setTitle(title)
    alert.setMessage(message)
    alert.setPositiveButton("Try Again") { _, _ -> tryAgainCallback?.invoke() }
    alert.setNeutralButton("OK") { _, _ -> listener?.invoke() }
    alert.show()

}