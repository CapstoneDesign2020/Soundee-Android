package com.soundee.soundee.data

import com.google.gson.JsonObject
import com.soundee.soundee.data.remote.RemoteDataSource
import com.soundee.soundee.data.vo.*

object RepositoryImpl : Repository{
    private val soundeeRemoteDataSource : RemoteDataSource=RemoteDataSource
    override fun postSignUp(
        body: JsonObject,
        onSuccess: (SignUpResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeRemoteDataSource.postSignUp(body,onSuccess,onFail)
    }

    override fun postSignIn(
        body: JsonObject,
        onSuccess: (SignInResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeRemoteDataSource.postSignIn(body, onSuccess, onFail)
    }

    override fun deleteUser(
        token: String,
        onSuccess: (DefaultResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeRemoteDataSource.deleteUser(token,  onSuccess, onFail)
    }

    override fun getDailyPieChart(
        token: String,
        onSuccess: (DailyPieChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeRemoteDataSource.getDailyPieChart(token, onSuccess, onFail)
    }

    override fun getWeeklyBarChart(
        token:String,
        onSuccess: (WeeklyBarChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeRemoteDataSource.getWeeklyBarChart(token, onSuccess, onFail)
    }

    override fun getMonthlyLineChart(
        token:String,
        onSuccess: (MonthlyLineChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeRemoteDataSource.getMonthlyLineChart(token, onSuccess, onFail)
    }

    override fun getPresentSound(
        token: String,
        onSuccess: (PresentSoundResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeRemoteDataSource.getPresentSound(token,onSuccess, onFail)
    }

    override fun deletePresentSound(
        token: String,
        soundIdx: Int,
        onSuccess: (DefaultResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeRemoteDataSource.deletePresentSound(token,soundIdx, onSuccess, onFail)
    }


}