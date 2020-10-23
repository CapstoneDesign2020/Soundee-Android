package com.soundee.soundee.chart

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soundee.soundee.R
import com.soundee.soundee.data.vo.ChartDetails

class ChartDetailsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = ArrayList<ChartDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DefaultSoundChartRecyclerViewHolder(parent)
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

    fun bind(data: ChartDetails) {

        sounNum.text = data.value.toString()+"회"
        drawBarColor(data.value, data.soundClass)
    }


    fun drawBarColor(soundNum: Int, name: String) {
        when (soundNum) {
            0 -> {
                bar.setImageResource(R.drawable.img_progressbar_default)
                icon.setImageResource(R.drawable.icon_default)
                icon.setBackgroundResource(R.drawable.border_gray_fill_10)
            }
            else -> {
                when (name) {
                    "water" -> {
                        soundName.text = "물 떨어지는 소리"
                        bar.setImageResource(R.drawable.img_progressbar_water)
                        icon.setImageResource(R.drawable.icon_water)
                        icon.setBackgroundResource(R.drawable.border_water_blue_fill_10)
                    }
                    "motor" -> {
                        soundName.text = "모터 소리"
                        bar.setImageResource(R.drawable.img_progressbar_motor)
                        icon.setImageResource(R.drawable.icon_motor)
                        icon.setBackgroundResource(R.drawable.border_motor_green_fill_10)
                    }
                    "baby" -> {
                        soundName.text = "아기 울음 소리"
                        bar.setImageResource(R.drawable.img_progressbar_baby_crying)
                        icon.setImageResource(R.drawable.icon_baby_crying)
                        icon.setBackgroundResource(R.drawable.border_baby_crying_orange_fill_10)
                    }
                    "siren" -> {
                        soundName.text = "사이렌 소리"
                        bar.setImageResource(R.drawable.img_progressbar_siren)
                        icon.setImageResource(R.drawable.icon_siren)
                        icon.setBackgroundResource(R.drawable.border_siren_red_fill_10)
                    }
                    else->{
                        bar.setImageResource(R.drawable.img_progressbar_default)
                        icon.setImageResource(R.drawable.icon_default)
                        icon.setBackgroundResource(R.drawable.border_gray_fill_10)
                    }
                }
            }
        }
    }
}

