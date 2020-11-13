package com.soundee.soundee.db


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.soundee.soundee.login.LoginActivity

object SoundeeUserController{

    private const val TOKEN = "token"
    private const val SOUNDIDX = "useridx"
    private const val USERNAME = "name"
    private const val  USEREMAIL= "email"
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
        return soundeeSharedPreferences.getString(TOKEN,"")
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

    fun setSoundIdx(ctx: Context, userIdx:Int){
        soundeeSharedPreferences=ctx.getSharedPreferences(SOUNDIDX,Context.MODE_PRIVATE)
        with(soundeeSharedPreferences.edit()){
            putInt(SOUNDIDX,userIdx)
            commit()
        }
    }

    fun getSoundIdx(ctx:Context?):Int{
        soundeeSharedPreferences= ctx!!.getSharedPreferences(SOUNDIDX,Context.MODE_PRIVATE)
        return soundeeSharedPreferences.getInt(SOUNDIDX,0 )
    }

    fun setName(ctx: Context, userName:String){
        soundeeSharedPreferences=ctx.getSharedPreferences(USERNAME,Context.MODE_PRIVATE)
        with(soundeeSharedPreferences.edit()){
            putString(USERNAME,userName)
            commit()
        }
    }

    fun getName(ctx:Context?):String?{
        soundeeSharedPreferences= ctx!!.getSharedPreferences(USERNAME,Context.MODE_PRIVATE)
        return soundeeSharedPreferences.getString(USERNAME,"" )
    }

    fun setEmail(ctx: Context, userEmail:String){
        soundeeSharedPreferences=ctx.getSharedPreferences(USEREMAIL,Context.MODE_PRIVATE)
        with(soundeeSharedPreferences.edit()){
            putString(USEREMAIL,userEmail)
            commit()
        }
    }

    fun getEmail(ctx:Context?):String?{
        soundeeSharedPreferences= ctx!!.getSharedPreferences(USEREMAIL,Context.MODE_PRIVATE)
        return soundeeSharedPreferences.getString(USEREMAIL,"" )
    }

}