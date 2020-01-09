package com.ahrybuk.swivltest.presentation.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import com.ahrybuk.swivltest.R
import com.ahrybuk.swivltest.api.entities.UserDetailEntity
import com.ahrybuk.swivltest.presentation.base.BaseFragment
import com.ahrybuk.swivltest.utils.openUrlInBrowser
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_user_detail.*

class UserDetailFragment : BaseFragment() {

    companion object {
        const val USERNAME_KEY = "username_key"
        const val USER_AVATAR_URL_KEY = "user_avatar_url_key"
    }

    private var username: String? = null
    private var userAvatarUrl: String? = null
    private lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        username = arguments?.getString(USERNAME_KEY)
        userAvatarUrl = arguments?.getString(USER_AVATAR_URL_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivAvatar.transitionName = "transition_".plus(username)

        preFill(view)
        subscribeToViewModel()
    }

    private fun preFill(rootView: View) {
        userAvatarUrl?.let { url ->
            Glide.with(rootView)
                .load(url)
                .into(ivAvatar)
        }
        setUpActionBar()
    }

    private fun setUpActionBar() {
        (activity as AppCompatActivity).run {
            view?.let {
                val innerToolbar = it.findViewById<Toolbar>(R.id.toolbar)
                setSupportActionBar(innerToolbar)
                supportActionBar?.title = username
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    private fun subscribeToViewModel() {
        if (username == null) {
//            TODO handle
            return
        }
        viewModel = ViewModelProviders.of(this, UserDetailViewModelProvider(username!!))[UserDetailViewModel::class.java]

        viewModel.loadingLiveData.observe(this, Observer {
            showLoading(it, loader)
        })
        viewModel.errorLiveData.observe(this, Observer {
            showError(it)
        })
        viewModel.userLiveData.observe(this, Observer {
            setUserInfo(it)
        })
    }

    private fun setUserInfo(userDetailEntity: UserDetailEntity) {
        tvRepos.text = getString(R.string.repos).plus(userDetailEntity.publicRepositories.toString())
        tvGists.text = getString(R.string.gists).plus(userDetailEntity.publicGists.toString())
        tvFollowers.text = getString(R.string.followers).plus(userDetailEntity.followers.toString())
        tvFullName.text = userDetailEntity.fullName
        tvUrl.apply {
            text = userDetailEntity.url
            setOnClickListener {
                openUrlInBrowser(context, userDetailEntity.url ?: "")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}