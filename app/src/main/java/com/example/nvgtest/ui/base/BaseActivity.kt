package vn.propzy.sam.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import io.reactivex.disposables.CompositeDisposable
import java.util.*

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : MvpActivity<V, P>(), BaseView {

    protected var blockAllTouchEvent = false
    abstract fun getActivityLayoutId(): Int

    open override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        setContentView(getActivityLayoutId())
        initView()
        initListener()
    }


    override fun getStringResource(resourceString: Int): String = resources.getString(resourceString)

    override fun getContext(): Context = this


    open fun initView() {

    }

    open fun initListener() {

    }



    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return blockAllTouchEvent || super.dispatchTouchEvent(ev)
    }

}