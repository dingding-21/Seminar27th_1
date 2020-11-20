package org.sophianing.seminar27th_1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.sophianing.seminar27th_1.fragment.FirstFragment
import org.sophianing.seminar27th_1.fragment.SecondFragment
import org.sophianing.seminar27th_1.fragment.ThirdFragment

class SampleViewPagerAdapter1(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> FirstFragment()
        1 -> SecondFragment()
        2 -> ThirdFragment()
        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = 3
}