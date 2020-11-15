 package org.sophianing.seminar27th_1.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.sophianing.seminar27th_1.BasicActivity
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.recycler.RecyclerActivity

class MainActivity : BasicActivity() {
    val REQUEST_SIGNUP = 100
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        gotoSignUp()
        autoLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_SIGNUP -> {
                    et_id_login.setText(data!!.getStringExtra("id").toString())
                    et_pwd_login.setText(data.getStringExtra("pwd").toString())
                }
            }
        }
    }

    fun gotoSignUp() {
        signup_btn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent,
                REQUEST_SIGNUP
            )
        }
    }

    fun autoLogin() {

        if (sharedPreferences.getString("ID_REM", "").isNullOrBlank()
            || sharedPreferences.getString("PWD_REM", "").isNullOrBlank()
        ) {
            login()
        } else {
            Toast.makeText(this, "자동로그인 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }
    }

    fun login() {

        login_btn.setOnClickListener {
            if (et_id_login.text.isNullOrBlank() || et_pwd_login.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디와 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show()
            } else {
                editor.putString("ID_REM", et_id_login.toString())
                editor.putString("PWD_REM", et_pwd_login.toString())
                editor.apply()

                Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, RecyclerActivity::class.java)
                startActivity(intent)
            }
        }
    }


}