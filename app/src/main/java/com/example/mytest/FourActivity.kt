package com.example.mytest

import android.graphics.Matrix
import android.graphics.PointF
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FourActivity : AppCompatActivity(),View.OnTouchListener{

    //缩放控制
    private var matrix: Matrix? = Matrix()
    private val savedMatrix = Matrix()

    //不同状态的表示
    private val NONE: Int = 0
    private val DRAG = 1
    private val ZOOM = 2
    private var mode = NONE

    // 定义第一个按下的点，两只接触点的重点，以及出事的两指按下的距离：
    private val startPoint = PointF()
    private var midPoint = PointF()
    private var oriDis = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)


        val img_test = findViewById<View>(R.id.face_left)
        img_test.setOnTouchListener(this);
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val view: ImageView = v as ImageView
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                matrix!!.set(view.getImageMatrix())
                savedMatrix.set(matrix)
                startPoint[event.x] = event.y
                mode = DRAG
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oriDis = distance(event)
                if (oriDis > 10f) {
                    savedMatrix.set(matrix)
                    midPoint = middle(event)
                    mode = ZOOM
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> mode = NONE
            MotionEvent.ACTION_MOVE -> if (mode === DRAG) {
                // 是一个手指拖动
                matrix!!.set(savedMatrix)
                matrix!!.postTranslate(event.x - startPoint.x, event.y - startPoint.y)
            } else if (mode === ZOOM) {
                // 两个手指滑动
                val newDist = distance(event)
                if (newDist > 10f) {
                    matrix!!.set(savedMatrix)
                    val scale = newDist / oriDis
                    matrix!!.postScale(scale, scale, midPoint.x, midPoint.y)
                }
            }
        }
        // 设置ImageView的Matrix
        view.setImageMatrix(matrix)
        return true
    }

    // 计算两个触摸点之间的距离
    private fun distance(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return Math.sqrt((x * x + y * y).toDouble()).toFloat()
    }

    // 计算两个触摸点的中点
    private fun middle(event: MotionEvent): PointF {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        return PointF(x / 2, y / 2)
    }
//        img_test.setOnTouchListener(object : View.OnTouchListener{
//            override fun onTouch(arg0: View?, event: MotionEvent): Boolean {
//                var curX:Int=0
//                var curY:Int=0
//                var mx:Int=0
//                var my:Int=0
//                when (event.action) {
//                    //手指初次接触到屏幕时触发
//                    MotionEvent.ACTION_DOWN -> {
//                        //记录点下去的点（起点）
//                        mx = event.rawX.toInt()
//                        my = event.rawY.toInt()
//                    }
//                    //手指在屏幕上滑动时触发，会多次触发
//                    MotionEvent.ACTION_MOVE -> {
//                        //记录移动后的点（终点）
//                        curX = event.rawX.toInt()
//                        curY= event.rawY.toInt()
//                        Log.e("orange", "curx:$curX ")
//                        Log.e("orange", "cury:$curY")
//                        switcherView.scrollBy(curX-mx, 0)
//                        mx = curX
//                        my = curY
//                    }
//                    //手指离开屏幕时触发
//                    MotionEvent.ACTION_UP -> {
//                        curX = event.rawX.toInt()
//                        curY = event.rawY.toInt()
//                        switcherView.scrollBy(curX-mx, 0)
//
//                    }
//                }
//                return true
//            }
//        })
//
//        switcherView.setOnClickListener{
//            Toast.makeText(this,"点击了", Toast.LENGTH_SHORT).show();
//        }
//        bindviews()



//    private var sb_normal: SeekBar? = null
//    fun bindviews() {
//        sb_normal = findViewById<View>(R.id.sb_normal) as SeekBar
//        sb_normal!!.max = 100
//        sb_normal!!.progress = 6
//        sb_normal!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
//            override fun onStartTrackingTouch(seekBar: SeekBar) {
////                Toast.makeText(mContext, "触碰SeekBar", Toast.LENGTH_SHORT).show();
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar) {
////                Toast.makeText(mContext, "放开SeekBar", Toast.LENGTH_SHORT).show();
//            }
//        })
//    }
}