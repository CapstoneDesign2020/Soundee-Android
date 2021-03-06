package com.soundee.soundee.data

import com.google.gson.JsonObject
import com.soundee.soundee.data.vo.*

interface Repository{
    //회원가입
    fun postSignUp(
        body:JsonObject,
        onSuccess: (SignUpResponse)->Unit,
        onFail : (errorMsg:String?)->Unit
    )

    //로그인
    fun postSignIn(
        body: JsonObject,
        onSuccess: (SignInResponse)->Unit,
        onFail : (errorMsg:String?)->Unit
    )

    //회월탈퇴
    fun deleteUser(
        token: String,
        onSuccess: (DefaultResponse)->Unit,
        onFail : (errorMsg:String?)->Unit
    )

    //일일 파이 차트
    fun getDailyPieChart(
        token:String,
        onSuccess: (DailyPieChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    )

    //주간 바 차트
    fun getWeeklyBarChart(
        token:String,
        onSuccess: (WeeklyBarChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    )

    //월간 라인 차트
    fun getMonthlyLineChart(
        token:String,
        onSuccess: (MonthlyLineChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    )

    //현재 들리는 소리 정보 요청
    fun getPresentSound(
        token: String,
        onSuccess: (PresentSoundResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    )

    //소리 정보 삭제 요청
    fun deletePresentSound(
        token: String,
        soundIdx:Int,
        onSuccess: (DefaultResponse)->Unit,
        onFail : (errorMsg:String?)->Unit
    )

}