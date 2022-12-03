package com.example.codigotravel.ui.util

class InputValidator {

    companion object {

        fun checkInput(input: String?, allowNullable: Boolean = false): InputResult {
            return if (input.isNullOrEmpty() && !allowNullable) InputResult.EMPTY else InputResult.VALID
        }

        fun isAllValid(vararg inputs: InputResult): Boolean = inputs.all { it == InputResult.VALID }
    }
}

enum class InputResult {
    EMPTY, VALID, INVALID
}