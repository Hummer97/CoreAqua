package com.xpertwebtech.coreaqua.base

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xpertwebtech.coreaqua.base.callback.AlertDialogCallback
import com.xpertwebtech.coreaqua.base.callback.ListDialogCallback
import com.google.android.material.snackbar.Snackbar
import com.rns.rnsecomapp.base.PreferenceManager
import com.xpertwebtech.coreaqua.R

abstract class BaseFragment<P : BaseView, T : BasePresenter<P>> : Fragment(),
    BaseView {
    var presenter: T? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getContentView(), container, false)
        this.presenter =
            BasePresenter.constructPresenter(
                this,
                getPresenterClass()
            )
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady(savedInstanceState)
        presenter!!.onViewReady()
    }

    override fun onResume() {
        super.onResume()
        presenter!!.initObservable()
    }

    override fun onPause() {
        super.onPause()
        presenter!!.destroyObservables()
    }

    protected abstract fun getContentView(): Int

    protected abstract fun getPresenterClass(): Class<T>

    protected abstract fun onViewReady(savedInstanceState: Bundle?)

    fun cancelKeyboard() {
        val imm = requireActivity().getSystemService(
                Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        var view = requireActivity().currentFocus
        if (view == null) {
            view = View(requireContext())
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private var progressDialog: ProgressDialog? = null

    fun showDialog(message: String?) {
        if (!requireActivity().isFinishing && !requireActivity().isDestroyed) {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(requireContext())
            }
            progressDialog!!.setTitle("Please Wait")
            progressDialog!!.setMessage(message)
            progressDialog!!.setCancelable(false)
            progressDialog!!.show()
        }
    }

    fun hideDialog() {
        if (!requireActivity().isFinishing && !requireActivity().isDestroyed) {
            progressDialog?.dismiss()
        }
    }

    fun showSingleListDialog(
            list: List<String?>,
            dialogId: Int,
            callback: ListDialogCallback
    ) {
        if (!requireActivity().isFinishing && !requireActivity().isDestroyed) {
            val dialogAdapter: ArrayAdapter<String?> =
                    ArrayAdapter(requireContext(), android.R.layout.select_dialog_singlechoice, list)
            val dialogBuilder =
                    AlertDialog.Builder(requireContext())
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
        if (!requireActivity().isFinishing && !requireActivity().isDestroyed) {
            val dialogAdapter: ArrayAdapter<String?> =
                    ArrayAdapter(requireContext(), android.R.layout.select_dialog_singlechoice, list)
            val dialogBuilder =
                    AlertDialog.Builder(requireContext())
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
        if (!requireActivity().isFinishing && !requireActivity().isDestroyed) {
            AlertDialog.Builder(requireContext())
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
        if (!requireActivity().isFinishing && !requireActivity().isDestroyed) {
            AlertDialog.Builder(requireContext())
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
        if (!requireActivity().isFinishing && !requireActivity().isDestroyed) {
            AlertDialog.Builder(requireContext())
                    .setTitle(if (title.isNotEmpty()) title else null)
                    .setMessage(if (message.isNotEmpty()) message else null)
                    .setCancelable(false)
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
        }
    }

    fun showSnackBar(message: String) {
        if (!requireActivity().isFinishing && !requireActivity().isDestroyed) {
            val snackbar: Snackbar = Snackbar.make(
                    this.view!!,
//                requireView().findViewById<View>(com.google.android.material.R.id.content),
                    message,
                    Snackbar.LENGTH_SHORT
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                snackbar.view
                        .setBackgroundColor(
                                resources.getColor(
                                        android.R.color.white,
                                        requireActivity().theme
                                )
                        )
            }
            val textView: TextView = snackbar.view.findViewById(R.id.snackbar_text)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(
                        resources.getColor(
                                android.R.color.black,
                                requireActivity().theme
                        )
                )
            }
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
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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
        return PreferenceManager.getInstance(requireContext())
    }

    override fun backPressed() {
        requireActivity().onBackPressed()
    }
}