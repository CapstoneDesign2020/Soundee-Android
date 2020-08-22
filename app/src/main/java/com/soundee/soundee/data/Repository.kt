package com.soundee.soundee.data

import com.google.gson.JsonObject
import com.soundee.soundee.data.vo.SignUpResponse

interface Repository{
    //회원가입
    fun postSignUp(
        body:JsonObject,
        onSuccess: (SignUpResponse)->Unit,
        onFail : (errorMsg:String)->Unit
    )

    //로그인
}