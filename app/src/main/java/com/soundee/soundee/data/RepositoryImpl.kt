package com.soundee.soundee.data

import com.google.gson.JsonObject
import com.soundee.soundee.data.remote.RemoteDataSource
import com.soundee.soundee.data.vo.SignUpResponse

object RepositoryImpl : Repository{
    private val soundeeRemoteDataSource : RemoteDataSource=RemoteDataSource
    override fun postSignUp(
        body: JsonObject,
        onSuccess: (SignUpResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        soundeeRemoteDataSource.postSignUp(body,onSuccess,onFail)
    }


}