package com.ahrybuk.swivltest.presentation.base

import android.view.View
import androidx.fragment.app.Fragment
import com.ahrybuk.swivltest.R
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {

    protected open fun showError(error: Throwable) {
        view?.let {
            Snackbar.make(
                it,
                error.localizedMessage ?: getString(R.string.unknown_error),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    protected open fun showLoading(isShown: Boolean, loaderView: View) {
        loaderView.animate()
            .alpha(if (isShown) 1f else 0f)
            .withStartAction {
                if (isShown) {
                    loaderView?.visibility = View.VISIBLE
                    loaderView?.alpha = 0f
                }
            }
            .withEndAction {
                if (!isShown) {
                    loaderView?.visibility = View.GONE
                }
            }
            .setDuration(600)
            .start()
    }
}