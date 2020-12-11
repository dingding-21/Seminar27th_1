package org.sophianing.seminar27th_1.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_recycler.*
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.adapter.SampleViewPagerAdapter1
import org.sophianing.seminar27th_1.fragment.FirstFragment
import org.sophianing.seminar27th_1.fragment.SecondFragment
import org.sophianing.seminar27th_1.fragment.ThirdFragment
import kotlin.properties.Delegates

class RecyclerActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: SampleViewPagerAdapter1
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        viewPagerAdapter =
            SampleViewPagerAdapter1(
                supportFragmentManager
            )
        sample_viewpager.adapter = viewPagerAdapter

//        sample_tab.setupWithViewPager(sample_viewpager)
//        sample_tab.apply {
//            getTabAt(0)?.text = "첫 번째"
//            getTabAt(1)?.text = "두 번째"
//            getTabAt(2)?.text = "세 번째"
//        }

        //뷰페이지를 슬라이드 했을 때 그에 대응되는 하단 탭 변경
        sample_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                sample_bottom_navi.menu.getItem(position).isChecked = true
            }

        })

        // 하단 탭을 눌렀을 때 뷰페이지 화면 변경
        sample_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()

            when (it.itemId) {
                R.id.menu_me -> index = 0
                R.id.menu_portfolio -> index = 1
                R.id.menu_setting -> index = 2

            }
            sample_viewpager.currentItem = index
            true
        }
    }
}