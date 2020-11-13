package com.soundee.soundee.data.remote

import android.util.Log
import com.google.gson.JsonObject
import com.soundee.soundee.data.Repository
import com.soundee.soundee.data.vo.*
import com.soundee.soundee.network.RequestNetworkInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource : Repository {

    private const val BASE_URL = "http://13.125.146.172"
    private val retrofit: Retrofit
    private val soundeeApiService: RequestNetworkInterface

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        soundeeApiService = retrofit.create(RequestNetworkInterface::class.java)
    }

    //회워가입
    override fun postSignUp(
        body: JsonObject,
        onSuccess: (SignUpResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeApiService
            .postSignUp(body)
            .enqueue(object : Callback<SignUpResponse> {
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    // 로그인 페이지로 이동
                    when (response.isSuccessful) {
                        true -> response.body()?.let { onSuccess(it) }
                        false -> onFail(response.errorBody()?.string())
                    }
                }

            })
    }

    //로그인
    override fun postSignIn(
        body: JsonObject,
        onSuccess: (SignInResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeApiService
            .postSignIn(body)
            .enqueue(object : Callback<SignInResponse> {
                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {

                    when (response.isSuccessful) {
                        true -> response.body()?.let { onSuccess(it) }
                        false -> {
                            if(response.code()==400){
                                Log.e("로그인 실패 400",response.errorBody()?.string())
                            }
                            onFail(response.errorBody()?.string())

                        }
                    }
                }
            })
    }

    //회원 탈퇴
    override fun deleteUser(
        token: String,
        onSuccess: (DefaultResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeApiService.deleteUser(token)
            .enqueue(object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    when (response.isSuccessful) {
                        true -> response.body()?.let { onSuccess(it) }
                        false -> onFail(response.errorBody()?.string())
                    }
                }
            })
    }

    override fun getDailyPieChart(
        token: String,
        onSuccess: (DailyPieChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeApiService.getDailyPieChart(token)
            .enqueue(object : Callback<DailyPieChartResponse> {
                override fun onFailure(call: Call<DailyPieChartResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<DailyPieChartResponse>,
                    response: Response<DailyPieChartResponse>
                ) {
                    when (response.isSuccessful) {
                        true -> response.body()?.let { onSuccess(it) }
                        false -> onFail(response.errorBody()?.string())
                    }
                }
            })
    }

    override fun getWeeklyBarChart(
        token: String,
        onSuccess: (WeeklyBarChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeApiService.getWeeklyBarChart(token)
            .enqueue(object : Callback<WeeklyBarChartResponse>{
                override fun onFailure(call: Call<WeeklyBarChartResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<WeeklyBarChartResponse>,
                    response: Response<WeeklyBarChartResponse>
                ) {
                    when (response.isSuccessful) {
                        true -> response.body()?.let { onSuccess(it) }
                        false -> onFail(response.errorBody()?.string())
                    }
                }

            })
    }

    override fun getMonthlyLineChart(
        token: String,
        onSuccess: (MonthlyLineChartResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeApiService.getMonthlyLineChart(token)
            .enqueue(object :Callback<MonthlyLineChartResponse>{
                override fun onFailure(call: Call<MonthlyLineChartResponse>, t: Throwable) {
                    onFail( t.toString())
                }

                override fun onResponse(
                    call: Call<MonthlyLineChartResponse>,
                    response: Response<MonthlyLineChartResponse>
                ) {
                    when (response.isSuccessful) {
                        true -> response.body()?.let { onSuccess(it) }
                        false -> onFail(response.errorBody()?.string())
                    }
                }

            })
    }

    override fun getPresentSound(
        token: String,
        onSuccess: (PresentSoundResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeApiService.getPresentSound(token)
            .enqueue(object :Callback<PresentSoundResponse>{
                override fun onFailure(call: Call<PresentSoundResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<PresentSoundResponse>,
                    response: Response<PresentSoundResponse>
                ) {

                    response.body()?.let { onSuccess(it) }
                    when (response.isSuccessful) {
                        true -> response.body()?.let { onSuccess(it) }
                        false -> onFail(response.errorBody()?.string())
                    }
                }
            })
    }

    override fun deletePresentSound(
        token: String,
        soundIdx: Int,
        onSuccess: (DefaultResponse) -> Unit,
        onFail: (errorMsg: String?) -> Unit
    ) {
        soundeeApiService.deletePresentSound(token, soundIdx)
            .enqueue(object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    onFail(t.toString())
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    when (response.isSuccessful) {
                        true -> response.body()?.let { onSuccess(it) }
                        false -> onFail(response.errorBody()?.string())
                    }
                }
            })
    }


}
