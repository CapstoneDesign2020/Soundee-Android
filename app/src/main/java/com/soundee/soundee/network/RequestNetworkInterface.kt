package com.soundee.soundee.network

import com.google.gson.JsonObject
import com.soundee.soundee.data.vo.*
import retrofit2.Call
import retrofit2.http.*

interface RequestNetworkInterface{

    //회원가입
    @POST("/user/signup")
    fun postSignUp(
        @Body() body: JsonObject
    ):Call<SignUpResponse>

    //로그인
    @POST("/user/signin")
    fun postSignIn(
        @Body() body: JsonObject
    ): Call<SignInResponse>

    //회원 탈퇴
    @DELETE("/user/withdraw")
    fun deleteUser(
        @Header("accessToken") token : String
    ):Call<DefaultResponse>

    //일일 파이 차트
    @GET("/chart/daily")
    fun getDailyPieChart(
        @Header("accessToken") token : String
    ):Call<DailyPieChartResponse>

    //주간 바 차트
    @GET("/chart/weekly")
    fun getWeeklyBarChart(
        @Header("accessToken") token:String
    ):Call<WeeklyBarChartResponse>

    //월간 바 차트
    @GET("/chart/monthly")
    fun getMonthlyLineChart(
        @Header("accessToken") token:String
    ):Call<MonthlyLineChartResponse>

    @GET("/sound")
    fun getPresentSound(
        @Header("accessToken") token:String
    ):Call<PresentSoundResponse>

    //소리 정보 삭제 요청
    @DELETE("/sound/{soundIdx}")
    fun deletePresentSound(
        @Header("accessToken") token:String,
        @Path("soundIdx")soundIdx:Int
    ):Call<DefaultResponse>


}