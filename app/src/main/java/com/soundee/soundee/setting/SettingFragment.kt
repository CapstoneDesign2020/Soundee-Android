package com.soundee.soundee.setting

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.soundee.soundee.R
import com.soundee.soundee.data.RepositoryImpl
import com.soundee.soundee.data.remote.RemoteDataSource
import com.soundee.soundee.db.SoundeeUserController
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_setting.*
import org.json.JSONObject

class SettingFragment : Fragment(R.layout.fragment_setting) {
    var a = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_setting_fold_account.setOnClickListener {

            if (a) {
                btn_setting_fold_account.isSelected = true
                layout_setting_unfold_account.visibility = View.VISIBLE
                a = false
            } else {
                layout_setting_unfold_account.visibility = View.GONE
                btn_setting_fold_account.isSelected = false
                a = true
            }
        }
        btn_setting_delete_account.setOnClickListener {
            deleteWithdrawUser()
        }


    }

    fun deleteWithdrawUser() {
        RemoteDataSource.deleteUser(SoundeeUserController.getToken(context)!!, SoundeeUserController.getUserIdx(context),
            {
                SoundeeUserController.clearToken(context!!)
                Toast.makeText(context, it.message, Toast.LENGTH_LONG)
            },
            {
            })
    }

}


