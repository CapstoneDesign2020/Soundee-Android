package com.soundee.soundee.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.soundee.soundee.R
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment(R.layout.fragment_setting) {
    var a = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_setting_fold_account.setOnClickListener {
            /*
            btn_setting_fold_account.setImageDrawable(resources.getDrawable(R.drawable.btn_unfolded_details))
            layout_setting_unfold_account.visibility= View.VISIBLE

            if(btn_setting_fold_account.drawable==resources.getDrawable(R.drawable.btn_folded_details)){
                btn_setting_fold_account.setImageDrawable(resources.getDrawable(R.drawable.btn_unfolded_details))
                layout_setting_unfold_account.visibility= View.VISIBLE
            }
             */



            if (a) {
                btn_setting_fold_account.isSelected = true
                layout_setting_unfold_account.visibility = View.VISIBLE
                a=false
            } else {
                layout_setting_unfold_account.visibility = View.GONE
                btn_setting_fold_account.isSelected = false
                a=true
            }


        }


    }

}


