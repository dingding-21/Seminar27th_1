package org.sophianing.seminar27th_1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.*
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.adapter.SampleViewPagerAdapter2

class FirstFragment : Fragment() {
    private lateinit var viewPaerAdapter2: SampleViewPagerAdapter2

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

        viewPaerAdapter2 = SampleViewPagerAdapter2(childFragmentManager)

        viewPaerAdapter2.fragments = listOf(
            Me_FirstFragment(),
            Me_SecondFragment()
        )
        sample_viewpager_2.adapter = viewPaerAdapter2

        sample_tab.setupWithViewPager(sample_viewpager_2)
        sample_tab.apply {
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }
    }

}