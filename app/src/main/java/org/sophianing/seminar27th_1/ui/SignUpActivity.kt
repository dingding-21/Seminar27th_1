package org.sophianing.seminar27th_1.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.signup_btn
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import org.sophianing.seminar27th_1.BasicActivity
import org.sophianing.seminar27th_1.R
import org.sophianing.seminar27th_1.api.SampleServiceImpl
import org.sophianing.seminar27th_1.data.RequestLoginData
import org.sophianing.seminar27th_1.data.RequestSignupData
import org.sophianing.seminar27th_1.data.ResponseLoginData
import org.sophianing.seminar27th_1.data.ResponseSignupData
import org.sophianing.seminar27th_1.recycler.RecyclerActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUp()
    }


    fun signUp() {
        signup_btn.setOnClickListener {
            val email = et_id_signup.text.toString()
            val password = et_pwd_signup.text.toString()
            val userName = et_name_signup.text.toString()

            val call: Call<ResponseSignupData> = SampleServiceImpl.service.postSignup(
                RequestSignupData(email = email, password = password, userName = userName)
            )

            call.enqueue(object : Callback<ResponseSignupData> {
                override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                    // 통신 실패 로직
                }

                // 통신 성공 로직
                override fun onResponse(
                    call: Call<ResponseSignupData>,
                    response: Response<ResponseSignupData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            it.data.let { data ->
                                Toast.makeText(
                                    this@SignUpActivity, "${data.userName}님",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent()
                                intent.putExtra("id", et_id_signup.text.toString())
                                intent.putExtra("pwd", et_pwd_signup.text.toString())
                                setResult(Activity.RESULT_OK, intent)
                                finish()
                            }
                        } ?: showError(response.errorBody())
                }
            }
            )

            if (et_name_signup.text.isNullOrBlank() || et_id_signup.text.isNullOrBlank() || et_pwd_signup.text.isNullOrBlank() || et_pwdcheck_signup.text.isNullOrBlank()) {
                Toast.makeText(this, "빈 칸이 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                if (et_pwd_signup.text.toString().equals(et_pwdcheck_signup.text.toString())) {
                    Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                    val intent = Intent()
                    intent.putExtra("id", et_id_signup.text.toString())
                    intent.putExtra("pwd", et_pwd_signup.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showError(error: ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"), Toast.LENGTH_SHORT).show()
    }
}

