package com.soundee.soundee.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.soundee.soundee.R
import com.soundee.soundee.db.SoundeeUserController
import com.soundee.soundee.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.e("dd", SoundeeUserController.getToken(context))
        if(SoundeeUserController.getToken(context)==null){
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }

        btn_home_no.setOnClickListener {
            selectedAnswerButton(btn_home_no)
            unselectedAnswerButton(btn_home_yes)
            createDialog(btn_home_no)
        }
        btn_home_yes.setOnClickListener {
            selectedAnswerButton((btn_home_yes))
            unselectedAnswerButton(btn_home_no)
            createDialog(btn_home_yes)
        }


    }

    private fun selectedAnswerButton(view: TextView) {
        view.setBackgroundResource(R.drawable.border_green_fill_35)
        view.setTextColor(resources.getColor(R.color.colorWhite))
    }
    private fun unselectedAnswerButton(view: TextView) {
        view.setBackgroundResource(R.drawable.border_green_line_35)
        view.setTextColor(resources.getColor(R.color.colorTextBlack))
    }

    private fun createDialog(view : TextView) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("피드백 감사합니다")
            .setNeutralButton("확인") {dialog,which->
               unselectedAnswerButton(view)
            }
        val alertDialog = builder.create()
        alertDialog.show()

    }


}

