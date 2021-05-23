package com.rns.rnsecomapp.api

import com.bb.bigbrother.api.base.BaseApiModel
import com.bb.bigbrother.api.base.ErrorApiModel
import com.xpertwebtech.coreaqua.base.BaseView
import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.SocketTimeoutException

abstract class ApiObserver<T>(message: String?, view: BaseView): DisposableObserver<T>() {

    private var weakReference: WeakReference<BaseView>? = null

    init {
        weakReference = WeakReference(view)
        view.showProgressDialog(message!!)
    }

    override fun onComplete() {
        weakReference!!.get()!!.hideProgressDialog()
    }

    override fun onNext(t: T) {
        weakReference!!.get()!!.hideProgressDialog()
        if (t != null) {
            if (t is BaseApiModel) {
                onResponse(t)
            } else onResponse(t)
        } else {
            weakReference!!.get()!!.onUnknownError("Something went wrong...")
        }
    }

    protected abstract fun onResponse(t: T)

    override fun onError(e: Throwable) {
        val view = weakReference!!.get()
        view!!.hideProgressDialog()
        if (e is HttpException) {
            val responseBody = e.response()!!.errorBody()
            if (responseBody != null) {
                getErrorMessage(responseBody)?.let { view.onUnknownError(it) }
            }
        } else if (e is SocketTimeoutException) {

            view.onTimeout()

        } else if (e is IOException) {

            view.onNetworkError(e.message!!)

        } else {

            view.onUnknownError(e.message!!)

        }
    }

    private fun getErrorMessage(responseBody: ResponseBody): String? {
        return try {
            val errorApiModel = Gson().fromJson(
                    responseBody.string(),
                    ErrorApiModel::class.java
            )
            errorApiModel.errorMessage
        } catch (e: Exception) {
            e.message
        }
    }
}