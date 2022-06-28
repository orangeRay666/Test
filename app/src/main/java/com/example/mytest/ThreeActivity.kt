package com.example.mytest

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class ThreeActivity : AppCompatActivity(){


    var url:String?=null
    private val BASE_URL = "http://123.56.232.18:8080/serverdemo";
    val client: OkHttpClient = OkHttpClient.Builder()    //builder构造者设计模式
        .connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
        .readTimeout(10, TimeUnit.SECONDS)    //读取超时
        .writeTimeout(10, TimeUnit.SECONDS)  //写超时，也就是请求超时
//        .addInterceptor(LoggingInterceptor())     //调用响应请求拦截器
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)


        getpic()


//        val img = findViewById<ImageView>(R.id.httppic)
//        Glide.with(this@ThreeActivity).load(url).into(img);
    }


    fun getpic() {    //网络请求接口
        //构造请求体
        val request: Request = Request.Builder()
            .url(BASE_URL +"/user/query?userId=1600932269")
            .build()
        //构造请求对象
        val call: Call = client.newCall(request)
        //发起异步请求enqueue--异步执行
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("okhttp","get response onFailure:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {
                    runOnUiThread(Runnable() {
                        val body = response.body?.string()
                        Log.e("okhttp", "get response :${body}")
                        val gson = Gson()
                        val userResponse =
                            gson.fromJson<UserResponse>(body, UserResponse::class.java)
//                    println("userResponse:$userResponse")
                        Log.e("aa", "avatar: ${userResponse.data.data.avatar}")
                        url = userResponse.data.data.avatar
                        Log.e("aa", "$url")
                        val img = findViewById<ImageView>(R.id.httppic)
                        Glide.with(this@ThreeActivity).load(url).into(img)

                    })
                }
            }
        })

    }


}


data class UserResponse(
    val data: Data,
    val message: String,
    val status: Int
)

data class Data(
    val data: User
//    val data: List<User>    data对象是个集合
)

data class User(
    val avatar: String,
    val commentCount: Int,
    val description: String,
    val expires_time: Long,
    val favoriteCount: Int,
    val feedCount: Int,
    val followCount: Int,
    val followerCount: Int,
    val hasFollow: Boolean,
    val historyCount: Int,
    val id: Int,
    val likeCount: Int,
    val name: String,
    val qqOpenId: String,
    val score: Int,
    val topCommentCount: Int,
    val userId: Int
)