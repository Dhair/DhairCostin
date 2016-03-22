package com.dhair.costin.ui.widget.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Creator: dengshengjin on 16/1/20 22:49
 * Email: deng.shengjin@zuimeia.com
 */
public class CustomView extends ImageView {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("CustomView ", "CustomView CustomView");
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("CustomView ", "CustomView onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("CustomView ", "CustomView onLayout");
    }

    @Override
    public void draw(Canvas canvas) {
        Log.e("CustomView ", "CustomView draw");
        super.draw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.e("CustomView ", "CustomView dispatchDraw");
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("CustomView ", "CustomView onDraw");
        super.onDraw(canvas);
    }

//    boolean draw(Canvas canvas, ViewGroup parent, long drawingTime){
//        Log.e("CustomView ", "CustomView draw(canvas,parent,drawingTime");
//    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        Log.e("CustomView ", "CustomView computeScroll");
        super.computeScroll();
    }
}
