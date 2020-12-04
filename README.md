##  :cherry_blossom: Seminar1 -  View와ViewGroup


### :large_orange_diamond: 실행 모습

 ![signup1](https://user-images.githubusercontent.com/63945197/97696881-8f5ef800-1ae9-11eb-89ae-d6b48314f4ad.gif) ![signup2](https://user-images.githubusercontent.com/63945197/97696834-79e9ce00-1ae9-11eb-9588-480cdedfce88.gif)

#####  과제완료: 20.10.25, 리드미 작성: 20.10.30
###   :white_check_mark: 필수과제: SignUpActivity 만들기 

>#### setOnClickListener,  Intent

```kotlin
signup_btn.setOnClickListener {  
	val intent = Intent(this, SignUpActivity::class.java)  
	startActivity(intent)  
}
```

 - setOnClickListener는 클릭 이벤트를 처리해주는 메서드로 signup_btn(회원가입) button을 누르면 { }안의 코드를 실행한다.

 - Intent 생성 - Intent( context, 호출할 액티비티 :: class.java ) 
   startActivity( )에 생성한 Intent객체를 파라미터 값으로 넣어서 호출하면 호출한   \
   액티비티로 화면이 전환된다.

>#### 모든 EditTextView에 데이터가 있는경우 / 하나라도 없을 경우

```kotlin
 fun signUp(){  
        signup_btn.setOnClickListener {  
	        if(et_name_signup.text.isNullOrBlank() || et_id_signup.text.isNullOrBlank() || et_pwd_signup.text.isNullOrBlank() || et_pwdcheck_signup.text.isNullOrBlank()){  
                Toast.makeText(this, "빈 칸이 있습니다.", Toast.LENGTH_SHORT).show()  
	        }  
            else{  
                if(et_pwd_signup.text.toString().equals(et_pwdcheck_signup.text.toString())){  
                    Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()       
                }  
                else{  
                    Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()  
                }  
            }  
        }  
  }
```

 - isNullOrBlank( )를 이용해 EditTextView에 데이터가 있는지 확인
 하나라도 없는경우 : 빈칸이 있다는 ToastMessage 출력
 모두 있는 경우: 회원가입이 완료되었다는 ToastMessage 출력

###   :white_check_mark: 성장과제1 : 화면이동 + @
 1. 회원가입에 성공하면 이전 로그인 화면으로 돌아오기
 2. 회원가입 후 아이디와 비밀번호가 입력되어 있기


> #### startActivityForResult( )

```kotlin
fun gotoSignUp() {  
    signup_btn.setOnClickListener {  
    val intent = Intent(this, SignUpActivity::class.java)  
        startActivityForResult(intent, REQUEST_SIGNUP)  
    }  
}
```

- startActivity : 새 액티비티를 열어준다. (단방향) 
- startActivityForResult : 새 액티비티를     열어주고 결과값을 전달한다.(쌍방향)
 - RequestCode: 액티비티를 식별하기 위한 값

  < SignUpActivity>
```kotlin
intent.putExtra("id", et_id_signup.text.toString())  
intent.putExtra("pwd", et_pwd_signup.text.toString())  
setResult(Activity.RESULT_OK, intent)  
finish()
```
 - putExtra( ): 데이터를 다음 액티비티에 전달한다.
 - setResult( ): 회원가입을 완료한 경우 resultCode를 Activity.RESULT_OK로 설정한 데이터를 넘겨주어 성공적으로 회원가입이 되었음을 보여준다.

  < MainActivity>

```kotlin
 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {  
	 super.onActivityResult(requestCode, resultCode, data)  
     if (resultCode == Activity.RESULT_OK) {  
	     when (requestCode) {  
                   REQUEST_SIGNUP -> {  
                       et_id_login.setText(data!!.getStringExtra("id").toString())  
                       et_pwd_login.setText(data!!.getStringExtra("pwd").toString())  
			}  
		}  
	}  
}
```
 - OnActivityResult( ) 메소드를 통해 호출된 Activity에서 저장한 결과를 받는다.
 - resultCode가 Activity.RESULT_OK이고 requestCode가 REQUEST_SIGNUP인 경우 
즉 회원가입이 성공적으로 완료된 경우 이전 로그인 화면에 아이디와 비밀번호가 입력되어있게 한다.

  ###   :white_check_mark: 성장과제2 : 자동로그인


 1. 로그인 버튼을 누르면 HomeActivity로 이동
 2. 로그인에 성공하는 순간 id와 password를 기억해서 다음 로그인 때 자동로그인 
 3. 자동로그인에 성공할 경우 자동로그인이 됐다는 메시지 출력

 

>####  SharedPreferences( )

   

```kotlin
     sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)  
     editor = sharedPreferences.edit()
```

 - getSharedPreferences( )를 이용해 SharedPreferences객체 생성
 - SharedPreferences로 데이터를 저장하기 위해 Editor 인터페이스 사용

 > #### 데이터 추가
```kotlin
      editor.putString("ID_REM", et_id_login.toString())  
      editor.putString("PWD_REM", et_pwd_login.toString())  
      editor.apply()
```
 - Editor 객체를 통해 원하는 값을 key, value 형태로 입력하고
apply( )를 해주면 데이터 저장이 완료된다. **(필수!)**

> #### 데이터 삭제
```kotlin
    editor.clear()  
    editor.commit()
```

 - clear()를 사용하면 SharedPreferences의 모든 데이터가 삭제되고  변경 후에는 commit을 해준다.

 

> 

##  :sunflower: Seminar2-  RecyclerView


### :large_orange_diamond: 실행 모습
![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/63945197/98329973-bfa01c80-203c-11eb-96e8-df62dcd33b0b.gif)    ![seminar2-22](https://user-images.githubusercontent.com/63945197/98329983-c3cc3a00-203c-11eb-93a4-8ca7194911ae.gif)
___

####   :white_check_mark: 필수과제: RecyclerView만들기, 상세보기 화면 구성 (20.10.25 완료)
 - RecyclerView에서 보여줄 **item Layout**
 - 각 아이템에 들어갈 데이터를 담은 **Data Class**
 - 데이터를 뷰에 뿌려주는 역할의 **ViewHolder**
 - 아이템마다 뷰홀더를 생성하고 그에 맞는 데이터를 전달하는 **Adapter**

>### Item Layout

![image](https://user-images.githubusercontent.com/63945197/98331557-24a94180-2040-11eb-8cbf-7dc8dc6931a2.png)
 이미지와 Title과 Subtitle 형식이 보여지는 *sample_item_list.xml*을 만든다.

​    

>### Data Class
```kotlin
data class SampleData(  
    val title : String,  
    val subTitle : String,  
    val date : String,  
    val detail : String  
)
```
RecyclerView에서 사용될 **title**변수, **subTitle** 변수, **date**변수, **detail**변수를 담은 데이터의 틀을 만든다.
>
> ###  ViewHolder
```kotlin
class SampleViewHolder (iTemView : View) : RecyclerView.ViewHolder(iTemView){  
   private val title: TextView = iTemView.findViewById(R.id.item_title)  
   private val subtitle: TextView = iTemView.findViewById(R.id.item_subtitle)  
  
   fun onBind(data : SampleData){  
		title.text = data.title  
		subtitle.text = data.subTitle  
	}  
}
```
**findViewById( )** 를 사용해서 item Layout에서 정의한 item_title과 item_subtitle을 요소로 가진다.
**onBind( )** 는 View에 Data Class에서 생성했던 title, subTitile의 데이터를 넣어준다.
> ### Adapter
> **반드시 오버라이드를 해줘야 하는 3가지 메소드**
1. onCreateViewHolder
 2. onBindViewHolder
 3. getItemCount

```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {  
  val view = LayoutInflater.from(context).inflate(R.layout.sample_item_list,parent, false)
  return SampleViewHolder(view)  
}
```
레이아웃을 만들고 해당 아이템에 관련된 자료를 담을 뷰 홀더를 만든다.
```kotlin
override fun getItemCount(): Int = data.size
```
리스트에 표시될 아이템의 개수를 리턴한다.
```kotlin
override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {  
    holder.onBind(data[position])  
  
    holder.itemView.setOnClickListener {  
		val model = data.get(position)  
	  
		var gTitle: String = model.title  
		var gSubTitle: String = model.subTitle  
		var gDate: String = model.date  
		var gDetail: String = model.detail  
	  
		val intent = Intent(context, DetailActivity::class.java)  
	  
	    intent.putExtra("iTitle", gTitle)  
	    intent.putExtra("iSubTitle", gSubTitle)  
	    intent.putExtra("iDate", gDate)  
	    intent.putExtra("iDetail", gDetail)  
	    
	    context.startActivity(intent)  
	 }  
} 
```
뷰 홀더가 실제로 연결(bind)될 때 호출되며 각 아이템을 누르면 해당 아이템의 세부 정보를 보여주는 DetailActivity로 데이터들을 넘겨준다.

> RecyclerActivity

```kotlin
class RecyclerActivity : AppCompatActivity() {
	private lateinit var sampleAdapter: SampleAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_recycler)
		
		sampleAdapter = SampleAdapter(this)
		
		main_rcv.adapter = sampleAdapter
		main_rcv.layoutManager = LinearLayoutManager(this)
		
		sampleAdapter.data = mutableListOf(  
		    SampleData("이름", "박현지", "생년월일: 1999.09.21", "주소: 일산동구 백석동 어딘가")
		)
		sampleAdapter.notifyDataSetChanged()
	}
}
```
RecycleView의 adapter에 sampleAdapter로 적용하고 RecyclerView가 순서대로 배치되도록 LinearLayoutManager로 설정한다.
SampleAdapter에 리스트로 보여줄 데이터(타이틀, 서브타이틀, 날짜, 세부정보)들을 넣어주고 Adapter에 데이터가 새로 만들어졌음을 알려준다.
___
####   :white_check_mark: 성장과제1 : GridLayout  만들기 (20.11.05 완료) 
< SampleAdapter >
```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {  
  
    val view = when(viewType){  
        1 -> LayoutInflater.from(context).inflate(R.layout.sample_item_list, parent,false)  
        2 -> LayoutInflater.from(context).inflate(R.layout.grid_item_list, parent,false)  
        else -> LayoutInflater.from(context).inflate(R.layout.sample_item_list, parent,false)  
    }  
    return SampleViewHolder(view)  
}

override fun getItemViewType(position: Int): Int {  
    return num  
}
```
** 3주차 필수과제 이후 실행해 코드가 Fragment에 있다.  
< SecondFragment>
```kotlin
override fun onCreateView(  
	...
    setHasOptionsMenu(true)  
	...
}
```

```kotlin
 override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {  
        super.onCreateOptionsMenu(menu, inflater)  
        
		var sub: Menu? = menu?.addSubMenu("sort")  
        sub?.add(Menu.NONE, Menu.FIRST + 3, Menu.NONE, "linear")  
        sub?.add(Menu.NONE, Menu.FIRST + 4, Menu.NONE, "grid")  
 }  
  
 override fun onOptionsItemSelected(item: MenuItem): Boolean {  
	 when(item?.itemId){  
		  Menu.FIRST + 3 -> {  
			  sampleAdapter.num = 1  
			  main_rcv.layoutManager = LinearLayoutManager(context)  
		  }  
		  Menu.FIRST + 4 ->{  
			  sampleAdapter.num = 2  
			  main_rcv.layoutManager = GridLayoutManager(context, 2)  
		  }  
	}  
	return super.onOptionsItemSelected(item)  
}
```
onCreateView에서 꼭 **setHasOptionsMenu(true)** 를 써야 fragment에서 menu를 사용할 수 있다.
onCreateOptionsMenu에서 submenu를 생성해 linear와 grid menu를 만들고 
onCreateViewHolder에서 num을 넘겨받아 그에 따른 linear, GridLayoutManager가 실행되도록 한다.



##    :rose: Seminar6 - Server


### :large_orange_diamond: 실행 모습
![ezgif com-gif-maker (7)](https://user-images.githubusercontent.com/63945197/101118200-f6615680-362b-11eb-8919-0eae85b67d62.gif)
![image](https://user-images.githubusercontent.com/63945197/101118385-4b9d6800-362c-11eb-89fc-ac96ffa134c6.png)![image](https://user-images.githubusercontent.com/63945197/101118475-74256200-362c-11eb-9a0e-96caffeff2c4.png)


---
####   :white_check_mark: 필수과제:  로그인 회원가입 서버 통신 수현 (20.11.29 완료)
 1. Retrofit Interface 설계

 2. 서버 Request / Response 객체 설계

 3. Retrofit Interface 실제 구현체 만들기

 4. Callback 등록하여 통신 요청

    

> Retrofit Interface 설계

```kotlin
 interface SampleService {  
    @Headers("Content-Type:application/json")  
    @POST("/users/signup")  
    fun postSignup(  
        @Body body: RequestSignupData  
  ):Call<ResponseSignupData>  
  
    @Headers("Content-Type:application/json")  
    @POST("/users/signin")  
  
    fun postLogin(  
        @Body body: RequestLoginData  
  ): Call<ResponseLoginData>  
}
```
식별 URL을 Interface로 설계

- ---

> 서버 Request / Response 객체 설계
 - RequestSignupData 
```kotlin
data class RequestSignupData(  
    val email : String,  
    val password : String,  
    val userName : String  
)
```

 - ResponseSignupData 
```kotlin
data class ResponseSignupData(  
    val data: Data,  
    val message: String,  
    val status: Int,  
    val success: Boolean  
) {  
    data class Data(  
        val email: String,  
        val password: String,  
        val userName: String  
  )  
}
```
 - RequestLoginData 
```kotlin
data class RequestLoginData(  
    val email : String,  
    val password : String  
)
```
 - ResponseLoginData 
```kotlin
data class ResponseLoginData(  
    val data: Data,  
    val message: String,  
    val status: Int,  
    val success: Boolean  
) {  
    data class Data(  
        val email: String,  
        val password: String,  
        val userName: String  
  )  
}
```
---
> Retrofit Interface 실제 구현체 만들기


```kotlin
object SampleServiceImpl {  
    private const val BASE_URL = "http://15.164.83.210:3000"  
  
  private val retrofit : Retrofit = Retrofit.Builder()  
        .baseUrl(BASE_URL)  
        .addConverterFactory(GsonConverterFactory.create())  
        .build()  
  
    val service : SampleService = retrofit.create(SampleService::class.java)  
}
```
싱글톤으로 만드는 실제 구현체 
 :  객체는 하나만 생성하고 프로젝트 어디서나 사용할 수 있게 한다.

-- -

> Callback 등록하여 통신 요청
- Call < Type> : 비동기적으로 Type을 받아오는 객체
-  Callback < Type > : Type 객체를 받아왔을 때 프로그래머의 행동
```kotlin
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
                    editor.putString("USER_NAME", data.userName)  
                    editor.putString("ID_REM", et_id_login.toString())  
                    editor.putString("PWD_REM", et_pwd_login.toString())  
                    editor.apply()  
  
                }  
  val intent = Intent(this@MainActivity, RecyclerActivity::class.java)  
                startActivity(intent)  
            } ?: showError(response.errorBody())  
    }  
}  
)
```
앞에서 만든 싱글톤 객체를 이용해서 Call 객체를 받아온 후 enqueue를 호출하여 실제 서버 통신을 비동기적으로 요청한다.