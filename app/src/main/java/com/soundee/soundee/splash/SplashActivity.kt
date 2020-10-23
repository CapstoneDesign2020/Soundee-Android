package com.soundee.soundee.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.soundee.soundee.MainActivity
import com.soundee.soundee.db.SoundeeUserController
import com.soundee.soundee.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("dd", SoundeeUserController.getToken(this))
        if(SoundeeUserController.getToken(this)==""){
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            Log.e("splash","토큰 값 없습니다")
        }
        else{
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            Log.e("splash","토큰 값 없습니다")
        }

    }

}