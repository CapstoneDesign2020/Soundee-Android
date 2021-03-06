package com.soundee.soundee.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.soundee.soundee.R
import com.soundee.soundee.data.remote.RemoteDataSource
import com.soundee.soundee.databinding.FragmentHomeBinding
import com.soundee.soundee.db.SoundeeUserController
import com.soundee.soundee.login.LoginActivity
import com.soundee.soundee.util.doWorkPeriodic5
import com.soundee.soundee.util.setAlarm
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    val vm = CurrentSoundViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentHomeBinding.inflate(inflater,container,false)
        binding.viewModel=vm
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        doWorkPeriodic5()
       // vm.getPresentSound(context!!)


        //메인에서 구독할거야
        //겂이 바뀌면
        vm.title.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                sender as ObservableField<String>
//                Log.e("옵저버","옵저버 잘들어왔냥:${sender.get()}")
//                Log.e("옵저버","옵저버 잘들어왔냥:${vm.title.get()}")
//                Log.e("옵저버","옵저버 잘들어왔냥:${txt_home_soundee.text}")
            }
        })

        val handler= Handler()
        val worker=Thread{
            for(i in 1..1000){
                handler.post {
                   CurrentSoundViewModel.getPresentSound(context!!)
                }
                Thread.sleep(5_000)
            }

        }
        worker.start()



        Log.e("dd", SoundeeUserController.getToken(context))
        if(SoundeeUserController.getToken(context)==null){
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }

        btn_home_no.setOnClickListener {
            selectedAnswerButton(btn_home_no)
            unselectedAnswerButton(btn_home_yes)
            unselectedAnswerButton(btn_home_now_using)

            deletePresentSound()
            createDialog()
        }
        btn_home_yes.setOnClickListener {
            selectedAnswerButton(btn_home_yes)
            unselectedAnswerButton(btn_home_no)
            unselectedAnswerButton(btn_home_now_using)
            createDialog()
        }
        btn_home_now_using.setOnClickListener {
            selectedAnswerButton(btn_home_now_using)
            unselectedAnswerButton(btn_home_yes)
            unselectedAnswerButton(btn_home_no)

            deletePresentSound()
            createDialog()

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

    private fun createDialog() {
        val builder = AlertDialog.Builder(context)

        builder.setView(R.layout.dialog_feedback)

        val alertDialog = builder.create()
        alertDialog.show()

    }

    fun deletePresentSound(){
        RemoteDataSource.deletePresentSound(SoundeeUserController.getToken(context)!!,SoundeeUserController.getSoundIdx(context)!!,{
            Log.e("소리 삭제 정보 요청",it.toString())
        },{})

    }


}

