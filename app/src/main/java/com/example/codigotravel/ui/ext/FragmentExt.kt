package com.example.codigotravel.ui.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.showToast(message: String, isLengthLong: Boolean = false) {
    Toast.makeText(
        requireContext(), message,
        if (isLengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    ).show()
}

fun Fragment.navigate(resId: Int) {
    findNavController().navigate(resId)
}