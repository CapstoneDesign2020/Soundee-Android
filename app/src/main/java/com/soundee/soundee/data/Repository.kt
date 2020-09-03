package com.soundee.soundee.data

import com.google.gson.JsonObject
import com.soundee.soundee.data.vo.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface Repository{
    //회원가입
    fun postSignUp(
        body:JsonObject,
        onSuccess: (SignUpResponse)->Unit,
        onFail : (errorMsg:String)->Unit
    )

    //로그인
    fun postSignIn(
        body: JsonObject,
        onSuccess: (SignInResponse)->Unit,
        onFail : (errorMsg:String)->Unit
    )

    //회월탈퇴
    fun deleteUser(
        token: String,
        userIdx:Int,
        onSuccess: (DeleteUserResponse)->Unit,
        onFail : (errorMsg:String)->Unit
    )

    //일일 파이 차트
    fun getDailyPieChart(
        token:String,
        onSuccess: (DailyPieChartResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    )

    //주간 바 차트
    fun getWeeklyBarChart(
        token:String,
        onSuccess: (WeeklyBarChartResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    )

    //월간 라인 차트
    fun getMonthlyLineChart(
        token:String,
        onSuccess: (MonthlyLineChartResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    )

}