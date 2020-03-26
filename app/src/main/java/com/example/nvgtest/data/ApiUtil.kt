package com.example.nvgtest.data

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ApiUtil {
    inline fun <reified R> makeRequestGet(link: String, paths: HashMap<String, String>? = null, queries: HashMap<String, String>? = null): Observable<R> {
        val builder = Rx2AndroidNetworking
            .get(link)
        if (paths != null && paths.size > 0)
            builder.addPathParameter(paths)
        if (queries != null && queries.size > 0)
            builder.addQueryParameter(queries)

        return builder
            .addHeaders("Accept-Language","en")
            .addHeaders("Content-type","application/json; charset=utf-8")
            .setPriority(Priority.MEDIUM)
            .build()
            .getObjectObservable(R::class.java)
            .subscribeOn(Schedulers.io())
    }

    inline fun <reified R> makeRequestGetList(link: String, paths: HashMap<String, String>? = null, queries: HashMap<String, String>?=null): Observable<MutableList<R>> {
            val builder = Rx2AndroidNetworking
                    .get(link)
            if (paths != null && paths.size > 0)
                builder.addPathParameter(paths)
            if (queries != null && queries.size > 0)
                builder.addQueryParameter(queries)

            return builder
                .addHeaders("Accept-Language","en")
                .addHeaders("Content-type","application/json; charset=utf-8")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getObjectListObservable(R::class.java)
                    .subscribeOn(Schedulers.io())

        }
}