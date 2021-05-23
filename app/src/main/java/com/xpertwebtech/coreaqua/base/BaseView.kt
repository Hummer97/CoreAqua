package com.xpertwebtech.coreaqua.base

import com.rns.rnsecomapp.base.PreferenceManager
import com.xpertwebtech.coreaqua.base.callback.AlertDialogCallback
import com.xpertwebtech.coreaqua.base.callback.ListDialogCallback
interface BaseView {
    fun onUnknownError(errorMessage: String)

    fun onTimeout()

    fun onNetworkError(error: String)

    fun showToast(message: String)

    fun showSnack(message: String)

    fun hideProgressDialog()

    fun showProgressDialog(message: String)

    fun hideKeyboard()

    fun showListDialog(
            list: List<String>,
            dialogId: Int,
            callback: ListDialogCallback
    )

    fun showListDialog(
            list: List<String>,
            title: String,
            dialogId: Int,
            callback: ListDialogCallback
    )

    fun showAlert(
            title: String,
            message: String,
            dialogId: Int,
            callback: AlertDialogCallback
    )

    fun showAlert(
            title: String,
            message: String,
            dialogId: Int,
            positiveButton: String,
            callback: AlertDialogCallback
    )

    fun showAlert(title: String, message: String)

    fun getPreferences(): PreferenceManager

    fun backPressed()
}