package com.example.nvgtest.ui.main

import com.example.nvgtest.data.model.response.Profile
import com.example.nvgtest.data.model.response.Repository
import vn.propzy.sam.ui.base.BaseView

interface MainView :BaseView {
    fun loadProfile(profile: Profile)
    fun updateListRepository(repositories:MutableList<Repository>)
}