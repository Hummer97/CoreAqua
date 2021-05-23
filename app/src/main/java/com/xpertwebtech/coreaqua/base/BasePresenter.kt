package com.xpertwebtech.coreaqua.base

import java.lang.ref.WeakReference

abstract class BasePresenter<T : BaseView>(view: T) {
    var view: WeakReference<T> = WeakReference(view)
    protected fun getView(): T {
        return view.get()!!
    }

    abstract fun initObservable()
    abstract fun destroyObservables()
    abstract fun onViewReady()

    companion object {
        fun <T : BaseView, P : BasePresenter<*>> constructPresenter(
                view: T,
                presenterClass: Class<P>
        ): P? {
            return try {
                presenterClass.getConstructor(view.javaClass).newInstance(view)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

}


