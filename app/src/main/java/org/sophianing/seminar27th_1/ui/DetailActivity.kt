package org.sophianing.seminar27th_1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import org.sophianing.seminar27th_1.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val pTitle = intent.getStringExtra("iTitle")
        val psubTitle = intent.getStringExtra("iSubTitle")
        val pDate = intent.getStringExtra("iDate")
        val pDetail = intent.getStringExtra("iDetail")

        title_name.text = pTitle
        subtitle_univ.text = psubTitle
        date.text = pDate
        detail.text = pDetail

    }


}