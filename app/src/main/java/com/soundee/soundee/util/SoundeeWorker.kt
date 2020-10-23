package com.soundee.soundee.util

import android.content.Context
import android.util.Log
import androidx.work.*
import com.soundee.soundee.home.CurrentSoundViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class SoundeeWorker(val ctx:Context,workerParams: WorkerParameters) :Worker(ctx,workerParams){
    override fun doWork(): Result {
        Log.e("work Manager","do Work"+ SimpleDateFormat("aa HH:mm", Locale.KOREAN).format(System.currentTimeMillis()))
        CurrentSoundViewModel.getPresentSound(ctx)
        //setAlarm(ctx)
       return Result.success()
    }

}

fun doWorkPeriodic5(){
    val workRequest=
       // OneTimeWorkRequestBuilder<SoundeeWorker>().setInitialDelay(30,TimeUnit.SECONDS).build()
    PeriodicWorkRequest.Builder(SoundeeWorker::class.java,15,TimeUnit.MINUTES).build()

    val workManager = WorkManager.getInstance()
    workManager?.enqueue(workRequest)
}

