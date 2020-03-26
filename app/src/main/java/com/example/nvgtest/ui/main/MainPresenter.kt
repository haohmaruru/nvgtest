package com.example.nvgtest.ui.main

import com.example.nvgtest.base.DisposableImpl
import com.example.nvgtest.data.ApiLink
import com.example.nvgtest.data.ApiUtil
import com.example.nvgtest.data.model.response.Profile
import com.example.nvgtest.data.model.response.Repository
import com.example.nvgtest.util.Constant
import com.example.nvgtest.util.ImageLoader
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import vn.propzy.sam.ui.base.BasePresenter

class MainPresenter : BasePresenter<MainView>() {
    var page = 1
    var pageSize = Constant.PAGE_SIZE
    var repositories = mutableListOf<Repository>()
    var isLoadMoreData = false

    fun getProfile() {
        ApiUtil.makeRequestGet<Profile>(ApiLink.PROFILE).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :
                DisposableImpl<Profile>() {
                override fun onNext(t: Profile) {
                    ifViewAttached { it.loadProfile(t) }
                }
            })
    }

    fun getRepos() {
        ApiUtil.makeRequestGetList<Repository>(String.format(ApiLink.GET_REPOSITORY,pageSize,page))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :
                DisposableImpl<MutableList<Repository>>() {
                override fun onNext(items: MutableList<Repository>) {
                    ifViewAttached {
                        if (items!=null && items.size>0)
                        {
                            items.get(0).pageNumber = page
                            repositories.addAll(items)
                            it.updateListRepository(items)
                        }

                    }
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                }
            })
    }

    fun  loadMore(){
        if (isLoadMoreData) return
        isLoadMoreData = true
        page++
        getRepos()
    }
}