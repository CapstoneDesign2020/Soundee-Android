package com.soundee.soundee.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import com.soundee.soundee.R
import com.soundee.soundee.util.CheckTextWatcher
import kotlinx.android.synthetic.main.actionbar_all_gotoback.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val textWatcher= CheckTextWatcher({checkEmailPassword()})
        txt_actionbar_name.text = "로그인"

        edt_login_email.setOnFocusChangeListener() { view, b -> haveFocus(view, b) }
        edt_login_password.setOnFocusChangeListener() { view, b -> haveFocus(view, b) }

        edt_login_email.addTextChangedListener(textWatcher)
        edt_login_password.addTextChangedListener(textWatcher)

        btn_login_signup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }




    private fun checkEmailPassword(){

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(edt_login_email.text).matches()) txt_login_check_email.visibility = View.INVISIBLE
        else txt_login_check_email.visibility = View.VISIBLE


        if (edt_login_password.text.isNotEmpty()) txt_login_check_password.visibility = View.INVISIBLE
        else txt_login_check_password.visibility = View.VISIBLE

        activeButton(btn_login_login,checkAll(),this)


    }

    private fun checkAll():Boolean{
       if (txt_login_check_email.visibility == View.VISIBLE || edt_login_email.text.isEmpty()) {
           Log.e("여기","1")
            edt_login_email.requestFocus()
           return   false
        } else if (txt_login_check_password.visibility == View.VISIBLE || edt_login_password.text.isEmpty()) {
           Log.e("여기","2")
            edt_login_password.requestFocus()
           return   false
        } else {
           Log.e("여기","3")
           return  true
        }
    }
}


fun haveFocus(view: View, b: Boolean) {
    if (b) view.setBackgroundResource(R.drawable.border_green_line_4)
    else view.setBackgroundResource(R.drawable.border_gray_line_4)
}

fun activeButton(view:TextView, b: Boolean,context: Context) {
    Log.e("여기",b.toString())
    if (b) {
        view.setBackgroundResource(R.drawable.border_green_fill_4)
        view.setTextColor(getColor(context,R.color.colorWhite))
        view.isEnabled = true
    } else {
        view.setBackgroundResource(R.drawable.border_green_line_4)
        view.setTextColor(getColor(context,R.color.colorTextBlack))
        view.isEnabled = false
    }
}
