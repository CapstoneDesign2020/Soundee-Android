package com.soundee.soundee.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.soundee.soundee.R
import com.soundee.soundee.data.RepositoryImpl
import com.soundee.soundee.util.CheckTextWatcher
import kotlinx.android.synthetic.main.actionbar_all_gotoback.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        txt_actionbar_name.text = "회원가입"
        txt_signup_check_password_again.visibility = View.INVISIBLE

        edt_signup_email.addTextChangedListener(CheckTextWatcher{checkEmail()})
        edt_signup_name.addTextChangedListener(CheckTextWatcher{checkNameLength20()})
        edt_signup_password.addTextChangedListener(CheckTextWatcher{checkPassword()})
        edt_signup_password_again.addTextChangedListener(CheckTextWatcher{checkPasswordAgain()})

        edt_signup_email.setOnFocusChangeListener(){view,b-> haveFocus(view,b)}
        edt_signup_name.setOnFocusChangeListener(){view,b-> haveFocus(view,b)}
        edt_signup_password.setOnFocusChangeListener(){view,b-> haveFocus(view,b)}
        edt_signup_password_again.setOnFocusChangeListener(){view,b-> haveFocus(view,b)}

        btn_signup_signup.setOnClickListener {
            moveFocus()
            postSignUp()


            //통신으로 대체되어야 함.
            //val intent= Intent(this, LoginActivity::class.java)
            //startActivity(intent)
        }


    }

    private fun checkEmail() {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(edt_signup_email.text).matches()) {
            txt_signup_check_email.visibility = View.INVISIBLE
        } else {
            txt_signup_check_email.visibility = View.VISIBLE
        }
        activeButton(btn_signup_signup,checkAll(),this)

    }

    private fun checkNameLength20() {
        if (edt_signup_name.text.length > 20) {
            txt_signup_check_name.visibility = View.VISIBLE
        } else txt_signup_check_name.visibility = View.INVISIBLE

        activeButton(btn_signup_signup,checkAll(),this)
    }

    private fun checkPassword() {

        if (Pattern.matches(
                "^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*\$",
                edt_signup_password.text.toString()
            )
        ) {
            txt_signup_check_password.visibility = View.INVISIBLE
        } else {
            txt_signup_check_password.visibility = View.VISIBLE
        }
        activeButton(btn_signup_signup,checkAll(),this)
    }


    private fun checkPasswordAgain() {
        if (edt_signup_password.text.toString() == edt_signup_password_again.text.toString()) {
            txt_signup_check_password_again.visibility = View.INVISIBLE
        } else {
            txt_signup_check_password_again.visibility = View.VISIBLE
            btn_signup_signup.isEnabled = false
        }
        activeButton(btn_signup_signup,checkAll(),this)
    }

    private fun checkAll():Boolean{
        return if(txt_signup_check_email.visibility==View.VISIBLE){
            false
        } else if(txt_signup_check_name.visibility==View.VISIBLE){
            false
        } else if(txt_signup_check_password.visibility== View.VISIBLE){
            false
        } else if(txt_signup_check_password_again.visibility==View.VISIBLE){
            false
        } else {
            true
        }
    }
    private fun  moveFocus(){
        if(txt_signup_check_email.visibility==View.VISIBLE || edt_signup_email.text.isEmpty()){
            edt_signup_email.requestFocus()
        }
        else if(txt_signup_check_name.visibility==View.VISIBLE || edt_signup_name.text.isEmpty()){
            edt_signup_name.requestFocus()
        }
        else if(txt_signup_check_password.visibility== View.VISIBLE||edt_signup_password.text.isEmpty()){
            edt_signup_password.requestFocus()
        }
        else if(txt_signup_check_password.visibility==View.VISIBLE|| edt_signup_password_again.text.isEmpty()){
            edt_signup_password_again.requestFocus()
        }
    }
    //통신
    private fun postSignUp(){
        val jsonObject =JSONObject()
        jsonObject.put("email",edt_signup_email.text.toString())
        jsonObject.put("password",edt_signup_password.text.toString())
        jsonObject.put("name",edt_signup_name.text.toString())

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        RepositoryImpl.postSignUp(gsonObject,{
            if(it.status==200){
                if(it.success){
                    Log.e("요청 값",gsonObject.toString())
                    Log.e("응답 값",it.toString())
                }
            }
        },{

        })
    }



}

