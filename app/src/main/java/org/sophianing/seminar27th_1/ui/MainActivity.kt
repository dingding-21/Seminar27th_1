package org.sophianing.seminar27th_1.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import org.sophianing.seminar27th_1.BasicActivity
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.api.SampleServiceImpl
import org.sophianing.seminar27th_1.data.RequestLoginData
import org.sophianing.seminar27th_1.data.ResponseLoginData
import org.sophianing.seminar27th_1.recycler.RecyclerActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            startActivityForResult(
                intent,
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
            val email = et_id_login.text.toString()
            val password = et_pwd_login.text.toString()

            val call: Call<ResponseLoginData> = SampleServiceImpl.service.postLogin(
                RequestLoginData(email = email, password = password)
            )

            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    // 통신 실패 로직
                }

                // 통신 성공 로직
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            it.data.let { data ->
                                Toast.makeText(
                                    this@MainActivity, "${data.userName}님",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            val intent = Intent(this@MainActivity, RecyclerActivity::class.java)
                            startActivity(intent)
                        } ?: showError(response.errorBody())
                }
            }
            )
//            if (et_id_login.text.isNullOrBlank() || et_pwd_login.text.isNullOrBlank()) {
//                Toast.makeText(this, "아이디와 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show()
//            } else {
//                editor.putString("ID_REM", et_id_login.toString())
//                editor.putString("PWD_REM", et_pwd_login.toString())
//                editor.apply()
//
//                Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
//                var intent = Intent(this, RecyclerActivity::class.java)
//                startActivity(intent)
        }
    }

    private fun showError(error: ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"), Toast.LENGTH_SHORT).show()
    }
}