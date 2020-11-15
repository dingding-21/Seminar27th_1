package org.sophianing.seminar27th_1.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.data.SampleData

class SampleViewHolder (iTemView : View) : RecyclerView.ViewHolder(iTemView){
   private val title:TextView = iTemView.findViewById(R.id.item_title)
    private val subtitle : TextView = iTemView.findViewById(R.id.item_subtitle)

    fun onBind(data : SampleData){
        title.text = data.title
        subtitle.text = data.subTitle
    }
}