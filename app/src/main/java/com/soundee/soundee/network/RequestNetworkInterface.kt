package com.soundee.soundee.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*
import com.soundee.soundee.data.vo.SignInResponse
import com.soundee.soundee.data.vo.SignUpResponse

interface RequestNetworkInterface{

    //로그인
    @GET("/user/signin")
    fun postSignIn(
        @Body() body: JsonObject
    ): Call<SignInResponse>

    //회원가입
    @POST("/user/signup")
    fun postSignUp(
        @Body() body: JsonObject
    ):Call<SignUpResponse>

}