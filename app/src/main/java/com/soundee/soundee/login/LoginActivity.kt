package com.soundee.soundee.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.soundee.soundee.R
import kotlinx.android.synthetic.main.actionbar_all_gotoback.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txt_actionbar_name.text = "로그인"

        edt_login_email.setOnFocusChangeListener() { view, b -> haveFocus(view, b) }
        edt_login_password.setOnFocusChangeListener() { view, b -> haveFocus(view, b) }

        edt_login_email.addTextChangedListener(textWatcher)
        edt_login_password.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            checkEmailPassword()
        }
    }


    private fun haveFocus(view: View, b: Boolean) {
        if (b) view.setBackgroundResource(R.drawable.border_green_line_4)
        else view.setBackgroundResource(R.drawable.border_gray_line_4)
    }

    private fun activeLoginButton(b: Boolean) {
        if (b) {
            btn_login_login.setBackgroundResource(R.drawable.border_green_fill_4)
            btn_login_login.setTextColor(getColor(R.color.colorWhite))
            btn_login_login.isEnabled = true
        } else {
            btn_login_login.setBackgroundResource(R.drawable.border_green_line_4)
            btn_login_login.setTextColor(getColor(R.color.colorTextBlack))
            btn_login_login.isEnabled = false
        }
    }

    private fun checkEmailPassword() {

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(edt_login_email.text).matches() && edt_login_password.text.toString().isNotEmpty()) {
            activeLoginButton(true)
        }

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(edt_login_email.text).matches()) txt_login_check_email.visibility = View.INVISIBLE
        else {
            txt_login_check_email.visibility = View.VISIBLE
            activeLoginButton(false)
        }

        if (edt_login_password.text.isNotEmpty()) txt_login_check_password.visibility = View.INVISIBLE
        else {
            txt_login_check_password.visibility = View.VISIBLE
            activeLoginButton(false)
        }

    }
}

