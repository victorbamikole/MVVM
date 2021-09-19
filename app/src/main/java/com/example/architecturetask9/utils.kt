package com.example.architecturetask9

import android.view.View
import com.google.android.material.snackbar.Snackbar

object utils {

    fun View.snackbar(message: String) {
        Snackbar.make(
            this,
            message,
            Snackbar.LENGTH_LONG
        ).also { snackbar ->
            snackbar.setAction("OK") {
                snackbar.dismiss()
            }
        }.show()
    }
}