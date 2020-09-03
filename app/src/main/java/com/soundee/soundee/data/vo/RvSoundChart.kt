package com.soundee.soundee.data.vo

import com.google.gson.annotations.SerializedName

data class RvSoundChart(

    @SerializedName("class")
    val soundClass :String,

    @SerializedName("date")
    val soundDate :String?,
    val value:Int
){
    companion object{
        const val DEFAULT=0
        const val EXIST=1
    }
}