package com.example.mytest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter (val context:Context,var data:List<Skin>) : RecyclerView.Adapter<MyRecyclerViewAdapter.VH>(){

    private var itemClickListener:IKotlinItemClickListener?=null

    //内部类 继承
    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val list_img: ImageView = v.findViewById((R.id.list_img))
        val list_text: TextView = v.findViewById(R.id.list_text)

    }


    //在该方法中创建ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        var vh = VH(view)

//        vh.itemView.setOnClickListener {
//            //拿到用户点击的位置
//            var position = vh.adapterPosition
//            var skin = data[position]
//            Toast.makeText(context, "你点击的了-》" + skin.text, Toast.LENGTH_SHORT).show()
//        }

        return vh
    }


    //返回listview的总列数
    override fun getItemCount(): Int {
        return data.size
    }

    //在该方法中数据绑定
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.list_text.setText(data.get(position).text)
        holder.list_img.setImageResource(data[position].ImgId)

        //添加点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(position: Int)
    }

}