package com.soundee.soundee.data.vo

data class SignInResponse(
    val status : Int,
    val success: Boolean,
    val message : String,
    val data : Token
)
data class Token(
    val accessToken:String
)