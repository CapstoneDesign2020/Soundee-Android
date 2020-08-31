package com.soundee.soundee.chart

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soundee.soundee.R
import com.soundee.soundee.data.vo.RvSoundChart
import java.lang.RuntimeException

class ChartDetailsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = ArrayList<RvSoundChart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RvSoundChart.DEFAULT -> DefaultSoundChartRecyclerViewHolder(parent)
            else -> throw RuntimeException("알 수 없는 뷰타입 에러")
        }

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        data[position].let {
            holder as DefaultSoundChartRecyclerViewHolder
            holder.bind(it)
        }

    }
}

class DefaultSoundChartRecyclerViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(viewGroup.context).inflate(
        R.layout.rv_chart_details,
        viewGroup,
        false
    )
) {
    val bar = itemView.findViewById<ImageView>(R.id.img_progressbar_sound)
    val icon = itemView.findViewById<ImageView>(R.id.img_icon_sound)
    val soundName = itemView.findViewById<TextView>(R.id.txt_sound)
    val sounNum = itemView.findViewById<TextView>(R.id.txt_sound_num)

    fun bind(data: RvSoundChart) {
        soundName.text = data.soundClass
        sounNum.text = data.value.toString()

        drawBarColor(data.value, data.soundClass)
    }

    fun drawBarColor(soundNum: Int, soundName: String) {
        when (soundNum) {
            0 -> {
                bar.setImageResource(R.drawable.img_progressbar_default)
                icon.setImageResource(R.drawable.icon_default)
                icon.setBackgroundResource(R.drawable.border_gray_fill_10)
            }
            else -> {
                when (soundName) {
                    "water" -> {
                        bar.setImageResource(R.drawable.img_progressbar_water)
                        icon.setImageResource(R.drawable.icon_water)
                        icon.setBackgroundResource(R.drawable.border_water_blue_fill_10)
                    }
                    "motor" -> {
                        bar.setImageResource(R.drawable.img_progressbar_motor)
                        icon.setImageResource(R.drawable.icon_motor)
                        icon.setBackgroundResource(R.drawable.border_motor_green_fill_10)
                    }
                    "baby_crying" -> {
                        bar.setImageResource(R.drawable.img_progressbar_baby_crying)
                        icon.setImageResource(R.drawable.icon_baby_crying)
                        icon.setBackgroundResource(R.drawable.border_baby_crying_orange_fill_10)
                    }
                    "drop_obj" -> {
                        bar.setImageResource(R.drawable.img_progressbar_drop_obj)
                        icon.setImageResource(R.drawable.icon_drop_obj)
                        icon.setBackgroundResource(R.drawable.border_drop_obj_red_fill_10)
                    }
                    "broken_glass" -> {
                        bar.setImageResource(R.drawable.img_progessbar_glass)
                        icon.setImageResource(R.drawable.icon_glass)
                        icon.setBackgroundResource(R.drawable.border_broken_glass_purple_fill_10)
                    }
                    "siren" -> {
                        bar.setImageResource(R.drawable.img_progressbar_siren)
                        icon.setImageResource(R.drawable.icon_siren)
                        icon.setBackgroundResource(R.drawable.border_siren_deep_purple_fill_10)
                    }
                }
            }
        }
    }
}

