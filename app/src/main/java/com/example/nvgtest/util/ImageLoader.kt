package com.example.nvgtest.util

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.example.nvgtest.base.App
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso

class ImageLoader {
    companion object {
        var picassoHttps: Picasso? = null

        fun getInstanceHttps(): Picasso? {
            if (picassoHttps == null) {
                val picassoBuilder = Picasso.Builder(App.context)
                picassoBuilder.downloader(OkHttpDownloader(
                        UnsafeOkHttpClient.getUnsafeOkHttpClient()
                ))

                picassoHttps = picassoBuilder.build()
            }
            return picassoHttps
        }

        fun loadDefautImage(context: Context, placeHolder: Int, targetImage: ImageView) {
            Picasso.with(context).load(placeHolder)
                    .placeholder(placeHolder)
                    .into(targetImage)
        }

        fun loadImageFromUrl(url: String?, context: Context, placeHolder: Int, targetImage: ImageView, targetWidth: Int, targetHeight: Int) {
            if (TextUtils.isEmpty(url))
                loadDefautImage(context, placeHolder, targetImage)
            else
                try {
                    if (url!!.toLowerCase().contains("https://")) {
                        getInstanceHttps()!!.load(url)
                                .placeholder(placeHolder)
                                .resize(targetWidth, targetHeight)
                                .centerCrop()
                                .into(targetImage)
                    } else {
                        Picasso.with(context).load(url)
                                .placeholder(placeHolder)
                                .resize(targetWidth, targetHeight)
                                .centerCrop()
                                .into(targetImage)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

        }

        fun loadImageFromUrlNoCache(url: String?, context: Context, placeHolder: Int, targetImage: ImageView, targetWidth: Int, targetHeight: Int) {
            if (TextUtils.isEmpty(url))
                loadDefautImage(context, placeHolder, targetImage)
            else
                try {
                    if (url!!.toLowerCase().contains("https://")) {

                        getInstanceHttps()!!.load(url)
                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                                .placeholder(placeHolder)
                                .resize(targetWidth, targetHeight)
                                .centerCrop()
                                .into(targetImage)
                    } else {
                        Picasso.with(context).load(url)
                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE)
                                .placeholder(placeHolder)
                                .resize(targetWidth, targetHeight)
                                .centerCrop()
                                .into(targetImage)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

        }

        fun loadImageFromUrl(url: String?, context: Context, placeHolder: Int, targetImage: ImageView) {
            if (TextUtils.isEmpty(url))
                loadDefautImage(context, placeHolder, targetImage)
            else
                try {
                    if (url!!.toLowerCase().contains("https://")) {

                        getInstanceHttps()!!.load(url)
                                .placeholder(placeHolder)
                                //.centerCrop()
                                .into(targetImage)
                    } else {
                        Picasso.with(context).load(url)
                                .placeholder(placeHolder)
                                //.centerCrop()
                                .into(targetImage)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

        }

        fun loadImageFromUrl(url: String, context: Context, targetImage: ImageView) {
            try {
                if (url.toLowerCase().contains("https://")) {

                    getInstanceHttps()!!.load(url)
                            // .centerCrop()
                            .into(targetImage)
                } else {
                    Picasso.with(context).load(url)
                            // .centerCrop()
                            .into(targetImage)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}