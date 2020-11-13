package com.soundee.soundee.setting

import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.soundee.soundee.R
import com.soundee.soundee.data.remote.RemoteDataSource
import com.soundee.soundee.db.SoundeeUserController
import com.soundee.soundee.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment(R.layout.fragment_setting) {
    var a = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txt_setting_user_name.text=SoundeeUserController.getName(context)
        txt_setting_user_email.text=SoundeeUserController.getEmail(context)

        btn_setting_fold_account.setOnClickListener {
            if (a) {
                btn_setting_fold_account.isSelected = true
                layout_setting_unfold_account.visibility = View.VISIBLE
                a = false
            } else {
                layout_setting_unfold_account.visibility = View.GONE
                btn_setting_fold_account.isSelected = false
                a = true
            }
            //vibrate(1)
        }
        btn_setting_logout.setOnClickListener {
            SoundeeUserController.clearToken(context!!)
            //val intent = Intent(context,LoginActivity::class.java)
            //startActivity(intent)
        }
        btn_setting_delete_account.setOnClickListener {
            deleteWithdrawUser()
        }
        //진동 토글버튼
        switch_setting_vibration.setOnCheckedChangeListener { buttonView, isChecked ->
            when (isChecked) {
                true -> {
                    txt_setting_vibration_strength.visibility = View.VISIBLE
                    seekBar_vibrator.visibility = View.VISIBLE
                    vibrate(1)
                }
                false -> {
                    txt_setting_vibration_strength.visibility = View.GONE
                    seekBar_vibrator.visibility = View.GONE
                }
            }
        }
        seekBar_vibrator.setOnSeekBarChangeListener(setVibrateSeekbar())

    }

    private fun deleteWithdrawUser() {
        RemoteDataSource.deleteUser(SoundeeUserController.getToken(context)!!,
            {
                SoundeeUserController.clearToken(context!!)
                Toast.makeText(context, it.message, Toast.LENGTH_LONG)
            },
            {
            })
    }

    fun vibrate(amplitude: Int) {
        val vib = context!!.getSystemService(VIBRATOR_SERVICE) as Vibrator
        vib.vibrate(VibrationEffect.createOneShot(amplitude.toLong() * 1000, (amplitude + 1) * 25))
    }

    fun setVibrateSeekbar(): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            var progressAmplitude = 1

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //vibrate 세기 바꾸기
                Log.e("진동 세기", progress.toString())
                progressAmplitude = progress
                // vibrate(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //값을 변경하기 위해 유저가 터치했을 때
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //값을 변경한 후 터치를 때었을 떄
                // vibrate(progressAmplitude)
                val vib2 = context!!.getSystemService(VIBRATOR_SERVICE) as Vibrator
                vib2.vibrate(
                    VibrationEffect.createWaveform(
                        longArrayOf(500, 1000, 500, 1000),
                        intArrayOf(0, 50, 0, 150),
                        -1
                    )
                )
            }
        }


    }


}


