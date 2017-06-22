package com.peerlessyu.peerless.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/6/22.
 */

public class DragView  extends View{
    private int lastX;
    private int lastY;


    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context) {
        super(context);
    }



    public boolean onTouchEvent(MotionEvent event) {

//        Log.d("付勇焜----->","TouchEvent");
//        Log.d("付勇焜----->",super.onTouchEvent(event)+"");


        //获取到手指处的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                lastX = x;
                lastY = y;

                break;

            case MotionEvent.ACTION_MOVE:

                //计算移动的距离
                int offX = x - lastX;
                int offY = y - lastY;
                //调用layout方法来重新放置它的位置
                layout(getLeft()+offX, getTop()+offY,
                        getRight()+offX    , getBottom()+offY);

                break;
        }

        return true;
    }
}
