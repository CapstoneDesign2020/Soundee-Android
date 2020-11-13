package com.soundee.soundee.home

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.databinding.ObservableField
import com.soundee.soundee.R
import com.soundee.soundee.data.RepositoryImpl
import com.soundee.soundee.db.SoundeeUserController
import com.soundee.soundee.util.setAlarm
import java.text.SimpleDateFormat
import java.util.*

object CurrentSoundViewModel {
    var title= ObservableField<String>("안녕하세요")
    var action= ObservableField("")
    var feedback = ObservableField("")
    var using=ObservableField<Boolean>()
    var button=ObservableField<Boolean>()
    //var right=ObservableField<Boolean>()

    fun getPresentSound(ctx: Context) {

        Log.e("title",title.get())

        if (SoundeeUserController.getToken(ctx) != "") {
            Log.e("햔재 소리 요청 토큰", SoundeeUserController.getToken(ctx)!!)

            RepositoryImpl.getPresentSound(SoundeeUserController.getToken(ctx)!!, {
                Log.e("현재 소리 성공", it.toString())
                Log.e("현재 소리 성공 시간", SimpleDateFormat("aa HH:mm:SS", Locale.KOREAN).format(System.currentTimeMillis()))
                if(it.status==200){
                    Log.e("실시간 소리 잘 받고 있음",it.toString())
                    Log.e("실시간 소리 잘 받고 있음",it.data[0].className.toString())
                    SoundeeUserController.setSoundIdx(ctx,it.data[0].soundIdx)
                    Log.e("소리 삭제",SoundeeUserController.getSoundIdx(ctx).toString())
                    when(it.data[0].className){
                        "water" -> {
                            title.set("물 소리가 들려요!")
                            action.set("물이 낭비 되고 있지는 않은지 확인해주세요!")
                            feedback.set("물소리가 맞다면 '맞아요!' 버튼을, 아니라면 '아니에요!' 버튼을 눌러주세요")
                            using.set(false)
                            button.set(true)
                            setAlarm(ctx,"물 소리가 들려요!")
                        }
                        "motor" -> {
                            title.set("모터 소리가 들려요!")
                            action.set("청소기나 드라이가 \n" +
                                    "켜져있는지 확인해주세요.")
                            feedback.set("켜져 있다면 '맞아요!' 버튼을,\n" +
                                    "아니라면 '아니에요!' 버튼을, \n" +
                                    "사용 중이랑면 '사용 중' 버튼을 눌러주세요")
                            using.set(true)
                            button.set(true)
                            setAlarm(ctx,"모터 소리가 들려요!")
                        }
                        "baby" -> {
                            title.set("아기 울음소리가 들려요!")
                            action.set("아기의 상태를 확인해주세요")
                            feedback.set("아기가 우는 게 맞다면 '맞아요!' 버튼을, 아니라면 '아니에요!' 버튼을 눌러주세요")
                            using.set(false)
                            button.set(true)
                            setAlarm(ctx,"아기 울음 소리가 들려요!")
                        }
                        "siren" -> {
                            title.set("사이렌 소리가 들려요!")
                            action.set("위험한 상황일 수 있으니 주위해주세요!")//여기 고쳐야함.
                            feedback.set("")
                            using.set(false)
                            button.set(false)
                            setAlarm(ctx,"사이렌 소리가 들려요!")
                        }
                    }
                }


            }, {
                if(it=="{\"status\":400,\"success\":false,\"message\":\"현재 소리 정보가 없습니다.\"}"){
                    Log.e("현재 소리 응답 400", it)
                    //val txt =Resources.getSystem().getString(R.string.soundee_default)
                    title.set("소리를 듣고 있어요")
                    //Log.e("title",title.get())
                    action.set("")
                    feedback.set("")
                    using.set(false)
                    button.set(false)

                }
            }
            )
        }
    }


}