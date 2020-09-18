package com.soundee.soundee.util

import android.content.Context
import android.util.Log
import androidx.work.*
import com.soundee.soundee.data.RepositoryImpl
import com.soundee.soundee.db.SoundeeUserController
import java.util.concurrent.TimeUnit

class SoundeeWorker(val ctx:Context,workerParams: WorkerParameters) :Worker(ctx,workerParams){
    override fun doWork(): Result {
       Log.e("work Manager","잘 되고 있군여")
        getPresentSound(ctx)

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

fun getPresentSound(ctx:Context) {
    if (SoundeeUserController.getToken(ctx) != "") {
        Log.e("햔재 소리 요청 토큰",SoundeeUserController.getToken(ctx)!!)

        RepositoryImpl.getPresentSound(SoundeeUserController.getToken(ctx)!!, {
            Log.e("현재 소리 요청 응답",it.toString())

        }, {
            Log.e("현재 소리 요청 응답 400",it)
        }
        )
    }
}