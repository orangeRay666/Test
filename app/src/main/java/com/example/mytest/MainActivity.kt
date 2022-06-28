package com.example.mytest

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.skin_result.*
import retrofit2.http.HTTP
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity(){

    private lateinit var adapter:MyRecyclerViewAdapter
    private var data = ArrayList<Skin>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //组件以代码的形式进行替换
        val mInflater = LayoutInflater.from(this);
        val view2: View = mInflater.inflate(
            R.layout.skin_result,
            box, false
        )
        var text = view2.findViewById<TextView>(R.id.text)
        text.setText("原图")
        box.addView(view2)


        val view3: View = mInflater.inflate(R.layout.skin_body,body,false)
        val view4: View = mInflater.inflate(R.layout.item_body_2,body,false)
        val body1 = view3.findViewById<FrameLayout>(R.id.body1)
        val body2 = view4.findViewById<FrameLayout>(R.id.body2)
        body.addView(view3)
        body.addView(view4)




        var btn1 = view3.findViewById<Button>(R.id.changebtn1)
        btn1?.setOnClickListener{
            Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
            body1.visibility=View.GONE
            body2.visibility=View.VISIBLE
//            body.removeView(view3)
//            body.addView(view4)


//            跳转到另一个activity
//            val intent= Intent(this,SecondActivity::class.java)
//            startActivity(intent)
        }

        var btn2 = view4.findViewById<Button>(R.id.changebtn2)
        btn2?.setOnClickListener{
            Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
            body2.visibility=View.GONE
            body1.visibility=View.VISIBLE
//            body.removeView(view4)
//            body.addView(view3)
        }

        var btn3 = view4.findViewById<Button>(R.id.changebtn3)
        btn3?.setOnClickListener {
            val intent1= Intent(this,SecondActivity::class.java)
            startActivity(intent1)
        }

        var httpbtn = view3.findViewById<Button>(R.id.httpbtn)
        httpbtn?.setOnClickListener{
            val intent2= Intent(this,ThreeActivity::class.java)
            startActivity(intent2)
        }



        val kkk2:ImageView =  view4.findViewById(R.id.kkk2);
        Glide.with(this).load(R.mipmap.kkkk).into(kkk2);

        //实现gif动图
        val kkk:ImageView =  view3.findViewById(R.id.kkk);
        Glide.with(this).load(R.mipmap.kkkk).placeholder(R.mipmap.kkkk).into(kkk);

//        Glide.with(this)/*在哪个页面显示*/
//            .load(url)/*加载的网络图片地址*/
//            .placeholder(R.drawable.loading2)/*预显示占位图*/
//            .dontAnimate()/*不进行图片的拉伸*/
//            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)/*强制让图片原大小显示*/
//            .into(imageView);/*在ImageView中显示*/


        //获取中间大图片id
        var face = view3.findViewById<ImageView>(R.id.face)
        //获取图片顶部文本
        var date_title = view3.findViewById<TextView>(R.id.date_title)
        var date_title2 = view4.findViewById<TextView>(R.id.date_title2)

        //获取当前时间
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)+1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)


        //recyclerview实现横向listview
        initData()
        adapter = MyRecyclerViewAdapter(this, data)
        //布局管理
        var manager = LinearLayoutManager(this)
        //设置成横向
        manager.orientation = LinearLayoutManager.HORIZONTAL

        lv.layoutManager = manager
        lv.adapter = adapter


        //recyclerview添加点击事件
        adapter!!.setOnKotlinItemClickListener((object:MyRecyclerViewAdapter.IKotlinItemClickListener{
            override fun onItemClickListener(position: Int) {
//                val skinImageSource = data[position].ImgId
//                face.setImageResource(skinImageSource)
                Toast.makeText(applicationContext, data!![position].text, Toast.LENGTH_SHORT).show()
                var skin:Skin = data[position]
                var face2 = view4.findViewById<ImageView>(R.id.face2)
                face2?.setImageResource(skin.ImgId)
                face?.setImageResource(skin.ImgId)
                date_title?.setText("$year-$month-$day   "+skin.text)
                date_title2?.setText("$year-$month-$day   "+skin.text)
            }
        }))



    }



    private fun initData() {
        data.add(Skin(R.mipmap.face,"原图"))
        data.add(Skin(R.mipmap.hzw,"色斑"))
        data.add(Skin(R.mipmap.face,"法令纹"))
        data.add(Skin(R.mipmap.face,"UV光"))
        data.add(Skin(R.mipmap.face,"蓝光"))
        data.add(Skin(R.mipmap.face,"粉刺"))
        data.add(Skin(R.mipmap.face,"座疮"))
        data.add(Skin(R.mipmap.face,"毛孔粗大"))
        data.add(Skin(R.mipmap.face,"痘痕"))


    }

//    fun click(view: View) {
//        when(view?.id){
//            R.id.changebtn1 -> {
//                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show()
//                val myInflater = LayoutInflater.from(this);
//                val view4: View = myInflater.inflate(
//                    R.layout.item_body_2,
//                    body, false
//                )
//                body.addView(view4)
//            }
//        }
//    }


}






