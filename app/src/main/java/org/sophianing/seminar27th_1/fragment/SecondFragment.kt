package org.sophianing.seminar27th_1.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_second.*
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.adapter.SampleAdapter
import org.sophianing.seminar27th_1.data.SampleData

class SecondFragment : Fragment() {
    private lateinit var sampleAdapter : SampleAdapter
    //lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_second, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

    //    menu?.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "logout")
//        menu?.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "코드메뉴2")

        var sub: Menu? = menu.addSubMenu("sort")
        sub?.add(Menu.NONE, Menu.FIRST + 3, Menu.NONE, "linear")
        sub?.add(Menu.NONE, Menu.FIRST + 4, Menu.NONE, "grid")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when(item.itemId){
//           Menu.FIRST + 1 -> {
//               editor.clear()
//               editor.commit()
//           }
           Menu.FIRST + 3 -> {
              sampleAdapter.NUM = 1
               main_rcv.layoutManager = LinearLayoutManager(context)
           }
           Menu.FIRST + 4 ->{
               sampleAdapter.NUM= 2
               main_rcv.layoutManager = GridLayoutManager(context, 2)
           }
       }

        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sampleAdapter = SampleAdapter(view.context)

        main_rcv.adapter = sampleAdapter
        main_rcv.layoutManager = LinearLayoutManager(view.context)
        //main_rcv.layoutManager = GridLayoutManager(view.context, 2)

        sampleAdapter.DATA = mutableListOf(
            SampleData(
                "이름",
                "박현지",
                "생년월일: 1999.09.21",
                "주소: 일산동구 백석동 어딘가"
            ),
            SampleData(
                "나이",
                "22",
                "학교: 홍익대학교 컴퓨터공학과",
                "아직 새내기같은 풋풋한 대학생"
            ),
            SampleData(
                "SOPT",
                "www.sopt.org",
                "-> SOPT 홈페이지",
                "Shout Our Passion Together"
            ),
            SampleData(
                "파트",
                "안드로이드",
                "27th SOPT활동",
                "YB"
            ),
            SampleData(
                "Instagram",
                "@sophialove._.21",
                "첫 게시물: 2018.09.10",
                "게시물보다 스토리 많이 올리는 편 *~*"
            ),
            SampleData(
                "뚜라미",
                "중앙 창작곡 동아리",
                "<주요 일정> 봄공연, 가을공연, 연합공연",
                "보컬, 키보드, 베이스, 드럼세션 공연 경험"
            )
        )
        sampleAdapter.notifyDataSetChanged()

//        changeGrid()

    }



//   fun changeGrid(){
//        change_btn.setOnClickListener{
//            var intent = Intent(this, GridRecyclerActivity::class.java)
//            startActivity(intent)
//        }
//    }
}