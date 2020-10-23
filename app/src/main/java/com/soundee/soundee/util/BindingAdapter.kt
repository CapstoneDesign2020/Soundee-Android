package com.soundee.soundee.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun setVisibility(view: View, visible:Boolean){
    if(visible){
        view.visibility=View.VISIBLE
    }
    else{
        view.visibility= View.GONE
    }
}
