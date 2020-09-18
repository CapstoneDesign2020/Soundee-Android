package com.soundee.soundee.data.vo

import com.google.gson.annotations.SerializedName

data class PresentSoundResponse(
    val status:Int,
    val success:Boolean,
    val message:String
   // val data : PresentSoundDetails?
)
data class PresentSoundDetails(
    val soundIdx : Int,
    @SerializedName("class")
    val className: String,
    @SerializedName("eventdate")
    val eventDate : String,
    @SerializedName("sound_userIdx")
    val userIdx : Int
)
