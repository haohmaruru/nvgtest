package com.example.nvgtest.ui.main

import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nvgtest.R
import com.example.nvgtest.customview.EndlessRecyclerViewScrollListener
import com.example.nvgtest.data.model.response.Profile
import com.example.nvgtest.data.model.response.Repository
import com.example.nvgtest.ui.adapter.RepositoryAdapter
import com.example.nvgtest.util.Constant
import com.example.nvgtest.util.ImageLoader
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import vn.propzy.sam.ui.base.BaseActivity


class MainActivity : BaseActivity<MainView,MainPresenter>(),MainView {
    private lateinit  var scrollListener: EndlessRecyclerViewScrollListener
    var adapter = RepositoryAdapter()

    override fun getActivityLayoutId(): Int  = R.layout.activity_main

    override fun createPresenter(): MainPresenter = MainPresenter()

    override fun initView() {
        adapter.repositories = getPresenter().repositories

        rvRepositories.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this)
        rvRepositories.setLayoutManager(linearLayoutManager)
        rvRepositories.setNestedScrollingEnabled(false)
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager,Constant.PAGE_SIZE) {
            override fun onLoadMore(
                page: Int,
                totalItemsCount: Int,
                view: RecyclerView
            ) {
                getPresenter().loadMore()
            }

            override fun onChangePage(page: Int) {
                if (page>1)
                {
                    header.visibility = View.VISIBLE
                    tvPageNumber.setText("${resources.getString(R.string.you_in_page)} ${page}")
                }
                else header.visibility = View.GONE
            }
        }
        rvRepositories.addOnScrollListener(scrollListener )
        getPresenter().getProfile()
        getPresenter().getRepos()
    }


    override fun loadProfile(profile: Profile) {
        tvName.setText(profile?.name)
        tvBlog.setText(profile?.blog)
        tvNumRepos.setText("${profile?.publicRepos}")
        tvEmail.setText(profile?.email)

        ImageLoader.loadImageFromUrl(profile!!.avatarUrl,this@MainActivity,avatar)
    }

    override fun updateListRepository(repositories: MutableList<Repository>) {
        adapter.notifyDataSetChanged()
        getPresenter().isLoadMoreData = false
    }

}
