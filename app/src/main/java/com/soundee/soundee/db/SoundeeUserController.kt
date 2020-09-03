package com.soundee.soundee.db


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.soundee.soundee.login.LoginActivity

object SoundeeUserController{

    private const val TOKEN = "token"
    private const val USERIDX = "useridx"
    private lateinit var soundeeSharedPreferences: SharedPreferences


    fun setToken(ctx: Context, token:String){
        soundeeSharedPreferences=ctx.getSharedPreferences(TOKEN,Context.MODE_PRIVATE)
        with(soundeeSharedPreferences.edit()){
            putString(TOKEN,token)
            commit()
        }
    }

    fun getToken(ctx:Context?):String?{
        soundeeSharedPreferences= ctx!!.getSharedPreferences(TOKEN,Context.MODE_PRIVATE)
        return soundeeSharedPreferences.getString(TOKEN,"" )
    }

    fun clearToken(ctx:Context){
        soundeeSharedPreferences=ctx.getSharedPreferences(TOKEN,Context.MODE_PRIVATE)

        val editor=soundeeSharedPreferences.edit()
            editor.clear()
            editor.commit()

        Log.e("토큰 지워졌나", getToken(ctx))

        val loginIntent = Intent(ctx,LoginActivity::class.java)
        ctx.startActivity(loginIntent)

    }

    fun setUserIdx(ctx: Context, userIdx:Int){
        soundeeSharedPreferences=ctx.getSharedPreferences(USERIDX,Context.MODE_PRIVATE)
        with(soundeeSharedPreferences.edit()){
            putInt(USERIDX,userIdx)
            commit()
        }
    }

    fun getUserIdx(ctx:Context?):Int{
        soundeeSharedPreferences= ctx!!.getSharedPreferences(USERIDX,Context.MODE_PRIVATE)
        return soundeeSharedPreferences.getInt(USERIDX,0 )
    }

}