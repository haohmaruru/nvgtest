package com.example.nvgtest.base

import android.app.Application
import android.content.Context
import com.androidnetworking.AndroidNetworking
import okhttp3.OkHttpClient


class App :Application() {
    companion object{
        lateinit var context:Context
    }
    override fun onCreate() {
        super.onCreate()
        context = this
        val okHttpClient = OkHttpClient().newBuilder()
            .build()
        AndroidNetworking.initialize(applicationContext, okHttpClient)
    }
}