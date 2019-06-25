package com.marzicst.android.mytestproject.utils

import android.app.ProgressDialog

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