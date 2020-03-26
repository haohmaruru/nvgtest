package com.example.nvgtest.base

import io.reactivex.observers.DisposableObserver

open class DisposableImpl<T>: DisposableObserver<T>() {
    override fun onComplete() {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }
}