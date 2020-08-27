package com.soundee.soundee.data.vo

import java.net.PasswordAuthentication

data class SignUpRequest(
    val email: String,
    val password:String,
    val name : String
)