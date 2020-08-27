package com.soundee.soundee.data

import com.google.gson.JsonObject
import com.soundee.soundee.data.remote.RemoteDataSource
import com.soundee.soundee.data.vo.*

object RepositoryImpl : Repository{
    private val soundeeRemoteDataSource : RemoteDataSource=RemoteDataSource
    override fun postSignUp(
        body: JsonObject,
        onSuccess: (SignUpResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeRemoteDataSource.postSignUp(body,onSuccess,onFail)
    }

    override fun postSignIn(
        body: JsonObject,
        onSuccess: (SignInResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeRemoteDataSource.postSignIn(body, onSuccess, onFail)
    }

    override fun deleteUser(
        token: String,
        userIdx: Int,
        onSuccess: (DeleteUserResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeRemoteDataSource.deleteUser(token, userIdx, onSuccess, onFail)
    }

    override fun getDailyPieChart(
        body: JsonObject,
        onSuccess: (DailyPieChartResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeRemoteDataSource.getDailyPieChart(body, onSuccess, onFail)
    }

    override fun getWeeklyBarChart(
        body: JsonObject,
        onSuccess: (WeeklyBarChartResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeRemoteDataSource.getWeeklyBarChart(body, onSuccess, onFail)
    }

    override fun getMonthlyLineChart(
        body: JsonObject,
        onSuccess: (MonthlyLineChartDetails) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeRemoteDataSource.getMonthlyLineChart(body, onSuccess, onFail)
    }


}