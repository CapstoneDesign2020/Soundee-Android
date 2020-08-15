package com.soundee.soundee.util


import android.text.Editable
import android.text.TextWatcher


class CheckTextWatcher  (private val change: () -> Unit) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        change()
    }
}
