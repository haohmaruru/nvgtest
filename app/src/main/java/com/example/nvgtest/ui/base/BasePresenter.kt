package vn.propzy.sam.ui.base

import com.example.nvgtest.base.DisposableImpl
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class BasePresenter<V : BaseView> : MvpBasePresenter<V>() {
    protected lateinit var compositeDisposable: CompositeDisposable
    var exitOnBackPressAgain = false
    fun setExitAppOnBackPress() {
        exitOnBackPressAgain = true
        compositeDisposable.add(
                Observable.just(1L)
                        .delay(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableImpl<Long>() {
                            override fun onNext(t: Long) {
                                exitOnBackPressAgain = false
                            }
                        }))
    }

    override fun attachView(view: V) {
        super.attachView(view)
        compositeDisposable = CompositeDisposable()
    }

    fun addSubscription(disposable: Disposable) = compositeDisposable.add(disposable)
    fun removeSubscription(disposable: Disposable) = compositeDisposable.remove(disposable)

    override fun detachView() {
        compositeDisposable.clear()
        super.detachView()
    }

    override fun destroy() {

    }

}