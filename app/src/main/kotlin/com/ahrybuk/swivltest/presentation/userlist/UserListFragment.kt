package com.ahrybuk.swivltest.presentation.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahrybuk.swivltest.R
import com.ahrybuk.swivltest.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : BaseFragment() {

    private lateinit var viewModel: UserListViewModel
    private val userListAdapter = UserListAdapter(this::onUserSelected)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater.inflate(R.layout.fragment_user_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSwipeRefreshLayout()
        setUpRecyclerView()
        subscribeToViewModel()
    }

    override fun onResume() {
        super.onResume()
        setUpActionBar()
    }

    private fun setUpSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            userListAdapter.update(arrayListOf())
            viewModel.loadInitial()
        }
        swipeRefreshLayout.setColorSchemeResources(
            R.color.accent,
            R.color.primary,
            R.color.primary_dark
        )
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        rvUsers.layoutManager = layoutManager
        rvUsers.adapter = userListAdapter
        setUpPagination(layoutManager)
    }

    private fun setUpPagination(layoutManager: LinearLayoutManager) {
        rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (loader.visibility == View.GONE) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 1
                    ) {
                        viewModel.loadNext()
                    }
                }
            }
        })
    }

    private fun subscribeToViewModel() {
        viewModel = ViewModelProviders.of(this)[UserListViewModel::class.java]
        viewModel.loadingLiveData.observe(this, Observer {
            showLoading(it, loader)
        })
        viewModel.errorLiveData.observe(this, Observer {
            showError(it)
        })
        viewModel.usersLiveData.observe(this, Observer {
            userListAdapter.update(it)
        })
    }

    private fun setUpActionBar() {
        (activity as AppCompatActivity).run {
            view?.let {
                val innerToolbar = it.findViewById<Toolbar>(R.id.toolbar)
                setSupportActionBar(innerToolbar)
                supportActionBar?.title = getString(R.string.user_list_screen_title)
            }
        }
    }

    private fun onUserSelected(userId: Long) {
        if (userId == -1L) {
            showError(Throwable("User have no id, sorry"))
        }
//        TODO() go next
    }
}