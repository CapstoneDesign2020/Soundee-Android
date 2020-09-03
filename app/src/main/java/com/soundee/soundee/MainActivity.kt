package com.soundee.soundee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.soundee.soundee.db.SoundeeUserController
import com.soundee.soundee.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("dd",SoundeeUserController.getToken(this))
        if(SoundeeUserController.getToken(this)==null){
            finish()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navi)
        val navigationController = Navigation.findNavController(this,R.id.nav_host_fragment)

        bottomNav.setupWithNavController(navigationController)
    }
}