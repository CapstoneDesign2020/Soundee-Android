package com.soundee.soundee.util

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class SoundeeWorker(ctx:Context,workerParams: WorkerParameters) :Worker(ctx,workerParams){
    override fun doWork(): Result {
       Log.e("work Manager","잘 되고 있군여")
       return Result.success()
    }

}
fun doWorkPeriodic5(){
    val workRequest= OneTimeWorkRequestBuilder<SoundeeWorker>()
        .setInitialDelay(30,TimeUnit.SECONDS)
        .build()
    PeriodicWorkRequest.Builder(SoundeeWorker::class.java,5,TimeUnit.SECONDS).build()

    val workManager = WorkManager.getInstance()

    workManager?.enqueue(workRequest)
}