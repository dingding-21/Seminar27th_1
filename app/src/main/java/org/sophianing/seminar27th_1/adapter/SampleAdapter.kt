package org.sophianing.seminar27th_1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sophianing.seminar27th_1.ui.DetailActivity
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.viewHolder.SampleViewHolder
import org.sophianing.seminar27th_1.data.SampleData

class SampleAdapter(private val context: Context) : RecyclerView.Adapter<SampleViewHolder>() {
     var DATA = listOf<SampleData>()
     var NUM: Int = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {

        val view = when (viewType) {
            1 -> LayoutInflater.from(context).inflate(R.layout.sample_item_list, parent, false)
            2 -> LayoutInflater.from(context).inflate(R.layout.grid_item_list, parent, false)
            else -> LayoutInflater.from(context).inflate(R.layout.sample_item_list, parent, false)
        }
        return SampleViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return NUM
    }

    override fun getItemCount(): Int = DATA.size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(DATA[position])

        holder.itemView.setOnClickListener {
            val model = DATA.get(position)

            var gTitle: String = model.title
            var gSubTitle: String = model.subTitle
            var gDate: String = model.date
            var gDetail: String = model.detail

            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra("iTitle", gTitle)
            intent.putExtra("iSubTitle", gSubTitle)
            intent.putExtra("iDate", gDate)
            intent.putExtra("iDetail", gDetail)

            context.startActivity(intent)
        }
    }
}