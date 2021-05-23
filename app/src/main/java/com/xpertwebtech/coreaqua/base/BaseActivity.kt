package com.xpertwebtech.coreaqua.base

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rns.rnsecomapp.base.PreferenceManager
import com.xpertwebtech.coreaqua.base.callback.AlertDialogCallback
import com.xpertwebtech.coreaqua.base.callback.ListDialogCallback
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.R

abstract class BaseActivity<P : BaseView, T : BasePresenter<P>> : AppCompatActivity(),
    BaseView {

    protected var presenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        presenter =
                BasePresenter.constructPresenter(
                        this,
                        getPresenterClass()
                )
        onViewReady(savedInstanceState)
        presenter!!.onViewReady()
    }


    override fun onResume() {
        super.onResume()
        presenter!!.initObservable()
    }

    override fun onPause() {
        presenter!!.destroyObservables()
        super.onPause()
    }

    protected abstract fun getContentView(): Int

    protected abstract fun getPresenterClass(): Class<T>

    protected abstract fun onViewReady(savedInstanceState: Bundle?)

    private var progressDialog: ProgressDialog? = null

    fun showDialog(message: String?) {
        if (!isFinishing && !isDestroyed) {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(this)
            }
            progressDialog!!.setTitle("Please Wait")
            progressDialog!!.setMessage(message)
            progressDialog!!.setCancelable(false)
            progressDialog!!.show()
        }
    }

    fun hideDialog() {
        if (!isFinishing && !isDestroyed) {
            progressDialog?.dismiss()
        }
    }

    fun showSingleListDialog(
            list: List<String?>,
            dialogId: Int,
            callback: ListDialogCallback
    ) {
        if (!isFinishing && !isDestroyed) {
            val dialogAdapter: ArrayAdapter<String?> =
                    ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, list)
            val dialogBuilder =
                    AlertDialog.Builder(this)
                            .setAdapter(
                                    dialogAdapter
                            ) { _: DialogInterface?, which: Int ->
                                callback.onItemSelected(
                                        which,
                                        list[which],
                                        dialogId
                                )
                            }
            dialogBuilder.setTitle(null)
            val alertDialog = dialogBuilder.create()
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            alertDialog.setOnDismissListener {
                callback.onDismiss(
                        dialogId
                )
            }
            alertDialog.show()
        }
    }

    fun showSingleListDialog(
            list: List<String?>,
            title: String?,
            dialogId: Int,
            callback: ListDialogCallback
    ) {
        if (!isFinishing && !isDestroyed) {
            val dialogAdapter: ArrayAdapter<String?> =
                    ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, list)
            val dialogBuilder =
                    AlertDialog.Builder(this)
                            .setAdapter(
                                    dialogAdapter
                            ) { _: DialogInterface?, which: Int ->
                                callback.onItemSelected(
                                        which,
                                        list[which],
                                        dialogId
                                )
                            }
            dialogBuilder.setTitle(null)
            val alertDialog = dialogBuilder.create()
            alertDialog.setTitle(title)
            alertDialog.setOnDismissListener {
                callback.onDismiss(
                        dialogId
                )
            }
            alertDialog.show()
        }
    }

    fun showAlertDialog(
            title: String,
            message: String,
            dialogId: Int,
            callback: AlertDialogCallback
    ) {
        if (!isFinishing && !isDestroyed) {
            AlertDialog.Builder(this)
                    .setTitle(if (title.isNotEmpty()) title else null)
                    .setMessage(if (message.isNotEmpty()) message else null)
                    .setCancelable(false)
                    .setPositiveButton(
                            "ok"
                    ) { _: DialogInterface?, _: Int ->
                        callback.onPositiveButton(
                                dialogId
                        )
                    }
                    .setNegativeButton(
                            "Cancel"
                    ) { _: DialogInterface?, _: Int ->
                        callback.onNegativeButton(
                                dialogId
                        )
                    }
                    .create()
                    .show()
        }
    }

    fun showAlertDialog(
            title: String,
            message: String,
            dialogId: Int,
            positiveButton: String?,
            callback: AlertDialogCallback
    ) {
        if (!isFinishing && !isDestroyed) {
            AlertDialog.Builder(this)
                    .setTitle(if (title.isNotEmpty()) title else null)
                    .setMessage(if (message.isNotEmpty()) message else null)
                    .setCancelable(false)
                    .setNegativeButton(
                            positiveButton
                    ) { _: DialogInterface?, _: Int ->
                        callback.onPositiveButton(
                                dialogId
                        )
                    }
                    .create()
                    .show()
        }
    }

    fun showAlertDialog(title: String, message: String) {
        if (!isFinishing && !isDestroyed) {
            AlertDialog.Builder(this)
                    .setTitle(if (title.isNotEmpty()) title else null)
                    .setMessage(if (message.isNotEmpty()) message else null)
                    .setCancelable(false)
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
        }
    }

    fun showSnackBar(message: String?) {
        if (!isFinishing && !isDestroyed) {
            val snackbar = Snackbar.make(
                    findViewById(android.R.id.content),
                    message!!,
                    Snackbar.LENGTH_SHORT
            )
            snackbar.view.setBackgroundResource(R.drawable.rectangle_bg)
//            snackbar.view.setBackgroundColor(resources.getColor(R.color.app_yellow))
            val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
            textView.setTextColor(resources.getColor(R.color.black))
            textView.setTypeface(null, Typeface.BOLD)
            snackbar.show()
        }
    }

    override fun onUnknownError(errorMessage: String) {
        showSnack(errorMessage)
    }

    override fun onTimeout() {
        showSnack("Timeout")
    }

    override fun onNetworkError(error: String) {
        if (error.contains("host")) showSnack("No Internet") else {
            showSnack(error)
        }
    }

    fun showToastMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun cancelKeyboard() {
        val imm = getSystemService(
                Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun showToast(message: String) {
        showToastMessage(message)
    }

    override fun showSnack(message: String) {
        showSnackBar(message)
    }

    override fun hideProgressDialog() {
        hideDialog()
    }

    override fun showProgressDialog(message: String) {
        showDialog(message)
    }

    override fun hideKeyboard() {
        cancelKeyboard()
    }

    override fun showListDialog(
            list: List<String>,
            dialogId: Int,
            callback: ListDialogCallback
    ) {
        showSingleListDialog(list, dialogId, callback)
    }

    override fun showListDialog(
            list: List<String>,
            title: String,
            dialogId: Int,
            callback: ListDialogCallback
    ) {
        showSingleListDialog(list, title, dialogId, callback)
    }

    override fun showAlert(
            title: String,
            message: String,
            dialogId: Int,
            callback: AlertDialogCallback
    ) {
        showAlertDialog(title, message, dialogId, callback)
    }

    override fun showAlert(
            title: String,
            message: String,
            dialogId: Int,
            positiveButton: String,
            callback: AlertDialogCallback
    ) {
        showAlertDialog(title, message, dialogId, positiveButton, callback)
    }

    override fun showAlert(title: String, message: String) {
        showAlertDialog(title, message)
    }

    override fun getPreferences(): PreferenceManager {
        return PreferenceManager.getInstance(this)
    }

    override fun backPressed() {
        onBackPressed()
    }

}

