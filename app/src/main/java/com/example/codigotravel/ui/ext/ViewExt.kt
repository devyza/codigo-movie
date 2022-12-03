package com.example.codigotravel.ui.ext

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getString() = this.editText?.text.toString()
