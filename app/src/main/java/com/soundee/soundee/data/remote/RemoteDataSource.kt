package com.soundee.soundee.data.remote

import com.google.gson.JsonObject
import com.soundee.soundee.data.Repository
import com.soundee.soundee.data.vo.*
import com.soundee.soundee.network.RequestNetworkInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource:Repository{

    private const val BASE_URL="http://13.125.146.172"
    private val retrofit:Retrofit
    private val soundeeApiService: RequestNetworkInterface

    init {
        retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        soundeeApiService= retrofit.create(RequestNetworkInterface::class.java)
    }

    //회워가입
    override fun postSignUp(
        body: JsonObject,
        onSuccess: (SignUpResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeApiService
            .postSignUp(body)
            .enqueue(object :Callback<SignUpResponse>{
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    // 로그인 페이지로 이동
                    when(response.isSuccessful){
                        true-> response.body()?.let { onSuccess(it) }
                        false-> onFail(response.errorBody().toString())
                    }
                }

            })
    }

    //로그인
    override fun postSignIn(
        body: JsonObject,
        onSuccess: (SignInResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeApiService
            .postSignIn(body)
            .enqueue(object :Callback<SignInResponse>{
                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    when(response.isSuccessful){
                        true-> response.body()?.let { onSuccess(it) }
                        false-> onFail(response.errorBody().toString())
                    }
                }
            })
    }

    //회원 탈퇴
    override fun deleteUser(
        token: String,
        userIdx: Int,
        onSuccess: (DeleteUserResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeApiService.deleteUser(token,userIdx)
            .enqueue(object :Callback<DeleteUserResponse>{
                override fun onFailure(call: Call<DeleteUserResponse>, t: Throwable) {
                    onFail(t.toString())
                }
                override fun onResponse(
                    call: Call<DeleteUserResponse>,
                    response: Response<DeleteUserResponse>
                ) {
                    when(response.isSuccessful){
                        true-> response.body()?.let { onSuccess(it) }
                        false-> onFail(response.errorBody().toString())
                    }
                }
            })
    }

    override fun getDailyPieChart(
        body: JsonObject,
        onSuccess: (DailyPieChartResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getWeeklyBarChart(
        body: JsonObject,
        onSuccess: (WeeklyBarChartResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getMonthlyLineChart(
        body: JsonObject,
        onSuccess: (MonthlyLineChartDetails) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        TODO("Not yet implemented")
    }


}
