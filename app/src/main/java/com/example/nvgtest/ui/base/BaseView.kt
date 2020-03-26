package vn.propzy.sam.ui.base

import android.content.Context
import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseView : MvpView {
    fun getContext(): Context
    fun getStringResource(resourceString: Int): String
}