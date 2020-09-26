package com.soundee.soundee.util

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.soundee.soundee.MainActivity
import com.soundee.soundee.chart.ChartFragment
import com.soundee.soundee.data.RepositoryImpl
import com.soundee.soundee.databinding.FragmentHomeBinding
import com.soundee.soundee.db.SoundeeUserController
import com.soundee.soundee.home.CurrentSoundViewModel
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit

class SoundeeWorker(val ctx:Context,workerParams: WorkerParameters) :Worker(ctx,workerParams){
    override fun doWork(): Result {
       Log.e("work Manager","잘 되고 있군여")
        CurrentSoundViewModel.getPresentSound(ctx)

       return Result.success()
    }

}

fun doWorkPeriodic5(){
    val workRequest= OneTimeWorkRequestBuilder<SoundeeWorker>()
        .setInitialDelay(30,TimeUnit.SECONDS)
        .build()
    //PeriodicWorkRequest.Builder(SoundeeWorker::class.java,5,TimeUnit.SECONDS).build()

    val workManager = WorkManager.getInstance()

    workManager?.enqueue(workRequest)
}

