package com.soundee.soundee.home

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.databinding.ObservableField
import com.soundee.soundee.R
import com.soundee.soundee.data.RepositoryImpl
import com.soundee.soundee.db.SoundeeUserController

object CurrentSoundViewModel {
    var title= ObservableField<String>("안녕하세요")
    var action= ObservableField("")
    var feedback = ObservableField("")

    fun getPresentSound(ctx: Context) {

        Log.e("title",title.get())

        if (SoundeeUserController.getToken(ctx) != "") {
            Log.e("햔재 소리 요청 토큰", SoundeeUserController.getToken(ctx)!!)

            RepositoryImpl.getPresentSound(SoundeeUserController.getToken(ctx)!!, {
                Log.e("현재 소리 성공", it.toString())
                if(it.status==200){
                    Log.e("실시간 소리 잘 받고 있음",it.toString())
                    Log.e("실시간 소리 잘 받고 있음",it.data[0].className.toString())
                    when(it.data[0].className){
                        "water" -> {
                            title.set("물 소리가 들려요!")
                        }
                        "motor" -> {
                            title.set("모터 소리가 들려요!")
                        }
                        "baby" -> {
                            title.set("아기 울음소리가 들려요!")
                        }
                        "drop" -> {
                            title.set("물건이 떨어지는 소리가 들려요!")
                        }
                        "glass" -> {
                            title.set("유리가 깨진 소리가 들려요!")
                        }
                        "siren" -> {
                            title.set("사이렌 소리가 들려요!")
                        }
                    }
                }


            }, {
                if(it=="{\"status\":400,\"success\":false,\"message\":\"현재 소리 정보가 없습니다.\"}"){
                    Log.e("현재 소리 요청 응답 400", it)
                    //val txt =Resources.getSystem().getString(R.string.soundee_default)
                    title.set("소리를 듣고 있어요")
                    Log.e("title",title.get())
                }
            }
            )
        }
    }


}