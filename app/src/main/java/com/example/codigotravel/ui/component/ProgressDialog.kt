package com.example.codigotravel.ui.component

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import com.example.codigotravel.R

class ProgressDialog(context: Context) {

    private var dialog: Dialog = Dialog(context)

    init {
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.layout_progressbar)
            findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
            setCanceledOnTouchOutside(false)
            setCancelable(false)
        }
    }

    fun show() { dialog.show() }
    fun hide() { dialog.hide() }
}