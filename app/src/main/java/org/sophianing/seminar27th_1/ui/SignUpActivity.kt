package org.sophianing.seminar27th_1.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.signup_btn
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.sophianing.seminar27th_1.BasicActivity
import org.sophianing.seminar27th_1.R

class SignUpActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUp()
    }


fun signUp(){
    signup_btn.setOnClickListener {
        if(et_name_signup.text.isNullOrBlank() || et_id_signup.text.isNullOrBlank() || et_pwd_signup.text.isNullOrBlank() || et_pwdcheck_signup.text.isNullOrBlank()){
            Toast.makeText(this, "빈 칸이 있습니다.", Toast.LENGTH_SHORT).show()
        }
        else{
            if(et_pwd_signup.text.toString().equals(et_pwdcheck_signup.text.toString())){
                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent()
                intent.putExtra("id", et_id_signup.text.toString())
                intent.putExtra("pwd", et_pwd_signup.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            else{
                Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
}

