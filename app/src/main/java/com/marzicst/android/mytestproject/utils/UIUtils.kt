package com.marzicst.android.mytestproject.utils

import android.app.ProgressDialog
import android.content.Context
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager

object UIUtils {

    fun showProgressDialog(dialog: ProgressDialog?, cancelable: Boolean){
        if (dialog == null || dialog.isShowing) return

        dialog.setCancelable(cancelable)
        dialog.show()
    }

    fun hideProgressDialog(dialog: ProgressDialog?){
        if (dialog != null && dialog.isShowing){
            dialog.cancel()
        }
    }
}