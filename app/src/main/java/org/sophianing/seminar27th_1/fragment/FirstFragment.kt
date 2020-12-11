package org.sophianing.seminar27th_1.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.*
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.adapter.SampleViewPagerAdapter2

class FirstFragment : Fragment() {
    private lateinit var viewPagerAdapter2: SampleViewPagerAdapter2
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = view.context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        viewPagerAdapter2 = SampleViewPagerAdapter2(childFragmentManager)

        sample_viewpager_2.adapter = viewPagerAdapter2
        me_name.text = sharedPreferences.getString("USER_NAME", "")

        sample_tab.setupWithViewPager(sample_viewpager_2)
        sample_tab.apply {
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }
    }
}