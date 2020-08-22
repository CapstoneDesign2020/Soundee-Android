package com.soundee.soundee.data.remote

import com.google.gson.JsonObject
import com.soundee.soundee.data.Repository
import com.soundee.soundee.data.vo.SignUpResponse
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

}
