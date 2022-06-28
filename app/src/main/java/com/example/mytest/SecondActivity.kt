package com.example.mytest

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.box
import kotlinx.android.synthetic.main.activity_main.lv
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.body2
import kotlinx.android.synthetic.main.double_picture.*


class SecondActivity : AppCompatActivity(){

    private lateinit var adapter:MyRecyclerViewAdapter
    private var data = ArrayList<Skin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        //组件以代码的形式进行替换
        val mInflater = LayoutInflater.from(this);
        val view2: View = mInflater.inflate(
            R.layout.skin_result,
            box, false
        )
        var text = view2.findViewById<TextView>(R.id.text)
        text.setText("我裂开了")
        box.addView(view2)

        val view3: View = mInflater.inflate(R.layout.double_picture,body2,false)
        body2.addView(view3)



        //实现gif动图
        val kkk3: ImageView =  findViewById(R.id.kkk3);
        Glide.with(this).load(R.mipmap.kkkk).placeholder(R.mipmap.kkkk).into(kkk3);

        val face_left:ImageView = view3.findViewById(R.id.face_left)
        val face_right:ImageView = view3.findViewById(R.id.face_right)
        Glide.with(this).load(R.mipmap.hzw).placeholder(R.mipmap.hzw).into(face_left)
        Glide.with(this).load(R.mipmap.hzw).placeholder(R.mipmap.hzw).into(face_right)




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
                var skin = data[position]
                face_left.setImageResource(skin.ImgId)
                face_right.setImageResource(skin.ImgId)
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
}