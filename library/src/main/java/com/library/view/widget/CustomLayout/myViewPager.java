package com.library.view.widget.CustomLayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.library.view.widget.CustomLayout.Helper.DistanceProvider;

/**
 * Created by xiaoye on 2016/4/16.
 */
public class myViewPager extends ViewGroup {


    private DistanceProvider distanceProvider;
    private GestureDetector detector;

    public myViewPager(Context context) {
        super(context);
    }

    public myViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        distanceProvider = new DistanceProvider(context);
        detector = new GestureDetector(context, new GestureDetector.OnGestureListener() {

            @Override
            /**
             * 有一个手指抬起的时候回调
             */
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            /**
             * 当有手指点击屏幕的时候
             */
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            /**
             * 当有手指在屏幕上滑动的时候回调
             */
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {

                // distanceX为正时，向左移动，为负时，向右移动
                // 移动屏幕的方法scrollBy，很重要，这个方法会调用onScrollChanged方法，并刷新视图
                /**
                 * dx表示x方向上移动的距离，dy表示y方向上移动的距离。往坐标轴正方向上移动的话，值就是正值；反之为负
                 */
                // scrollBy内部实际上是重写了scrollTo方法，scrollTo是将当前视图的基准点移动到某个坐标点
                scrollBy((int) distanceX, 0);

                return false;
            }

            @Override
            /**
             * 当有手指在屏幕上长按的时候回调
             */
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            /**
             * 快速滑动的时候回调
             */
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                                   float velocityX, float velocityY) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            /**
             * 按下的时候回调的方法
             */
            public boolean onDown(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }
        });

        post(new Runnable() {
            @Override
            public void run() {
                scrollTo(getMeasuredHeight() * 100, 0);
            }
        });
    }

    public myViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
/**
 * 对子view进行布局排列，确定子view的位置
 * @param changed 代表调用onLayout方法时，判断当前布局有没有发生改变，如果发生改变了就为true，否则false
 * @param int l, int t, int r, int b代表当前view也就是我们的myviewpager，相对于它的父view的位置，在这里我们在排列自己的子view的时候
 * 这几个参数基本没什么用
 */
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // ①首先拿到所有的子view
        for (int i = 0, size = getChildCount(); size > i; i++) {
            View view = getChildAt(i);// 取得下表为i的子view

            // ② 对子view进行排列，实际上就是需要调用子view的layout方法，四个参数分别对应 左、上、右、下
            view.layout(i * getWidth(), 0, getWidth() * (i + 1), getHeight());

        }
    }

    /**
     * down事件时的x坐标
     */
    private int firstX;

    private int currentId = 0;//当前显示的图片的id，从0开始，最大值为getChildCount()-1

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        // 使用工具来解析触摸事件
        detector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                int tmpId = 0;
                // 手指向右滑动超过屏幕的1/2，当前的id应该-1
                if (event.getX() - firstX > getWidth() / 2) {
                    tmpId = currentId - 1;
                } else if (firstX - event.getX() > getWidth() / 2) {
                    tmpId = currentId + 1;
                } else {
                    tmpId = currentId;
                }

                // 三目运算符的效率比if else效率要高很多
                int childCount = getChildCount();
                currentId = tmpId;
//                        < 0 ? 0
//                        : ((tmpId > childCount - 1) ? childCount - 1 : tmpId);

//                scrollTo(currentId * getWidth(), 0);
                moveToDest();
                break;

            default:
                break;
        }

        // 消费掉本次事件
        return true;
    }

    /**
     * 移动到合理的位置的方法
     */
    private void moveToDest() {
        /**
         * 移动起点到终点的距离
         */
        int distance = currentId * getWidth() - getScrollX();

        /**
         * 在一段时间里面，平滑移动这段距离
         */
        // 开启计算偏移量
        distanceProvider.startScroll(getScrollX(), 0, distance, 0);

        invalidate();// invalidate会导致ondraw和computeScroll方法的执行


    }

    @Override
    public void computeScroll() {
        // super.computeScroll();
        // 计算滑动偏移量
        System.out.println(getScrollX() + "  ScrollX");
        if (!distanceProvider.computeScrollOffset()) {
            int newX = (int) distanceProvider.getCurrentX();
            scrollTo(newX, 0);
            // 再次刷新
            invalidate();
        } else {
            int width = getMeasuredWidth();
            if (getScrollX() % width != 0) return;


            int height = getMeasuredHeight();

            int position = getScrollX() / width;

            if (getScrollX() >= 0)

                switch (position % 3) {
                    case 0:
                        getChildAt(1).layout(getScrollX() + width, 0, getScrollX() + width * 2, height);
                        getChildAt(2).layout(getScrollX() - width, 0, getScrollX(), height);
                        break;
                    case 1:
                        getChildAt(0).layout(getScrollX() - width, 0, getScrollX(), height);
                        getChildAt(2).layout(getScrollX() + width, 0, getScrollX() + width * 2, height);
                        break;
                    case 2:
                        getChildAt(1).layout(getScrollX() - width, 0, getScrollX(), height);
                        getChildAt(0).layout(getScrollX() + width, 0, getScrollX() + width * 2, height);
                        break;
                }
//            else {
//                switch (position % 3) {
//                    case 0:
//                        getChildAt(1).layout(getScrollX() + width, 0, getScrollX() + width * 2, height);
//                        getChildAt(2).layout(getScrollX() - width, 0, getScrollX(), height);
//                        break;
//                    case 1:
//                        getChildAt(0).layout(getScrollX() - width, 0, getScrollX(), height);
//                        getChildAt(2).layout(getScrollX() + width, 0, getScrollX() + width * 2, height);
//                        break;
//                    case 2:
//                        getChildAt(1).layout(getScrollX() - width, 0, getScrollX(), height);
//                        getChildAt(0).layout(getScrollX() + width, 0, getScrollX() + width * 2, height);
//                        break;
//                }

//            // ①首先拿到所有的子view
//            for (int i = 0, size = getChildCount(); size > i; i++) {
//                View view = getChildAt(i);// 取得下表为i的子view
////                view.layout((i + position - 1) * width, 0, (i + position) * width, height);
//
//                int index = position % 3;
//
//                if (index == 0) {
//
//                    if (i == 0) {
//                        view.layout(getScrollX(), 0, getScrollX() + width, height);
//                    } else if (i == 1) {
//                        view.layout(width + getScrollX(), 0, getScrollX() + width + width, height);
//                    } else {
//                        view.layout(getScrollX() - width, 0, getScrollX(), height);
//                    }
//
//                } else if (index == 1) {
//                    if (i == 0) {
//                        view.layout(getScrollX(), 0, getScrollX() + width, height);
//                    } else if (i == 1) {
//                        view.layout(width + getScrollX(), 0, getScrollX() + width + width, height);
//                    } else {
//                        view.layout(getScrollX() + width + width, 0, getScrollX() + width + width + width, height);
//                    }
//                } else {
//                    if (i == 0) {
//                        view.layout(getScrollX() + width, 0, getScrollX() + width + width, height);
//                    } else if (i == 1) {
//                        view.layout(getScrollX(), 0, getScrollX() + width, height);
//                    } else {
//                        view.layout(getScrollX() - width, 0, getScrollX(), height);
//                    }
//                }
//
//            }


//                // ② 对子view进行排列，实际上就是需要调用子view的layout方法，四个参数分别对应 左、上、右、下
////                view.layout(i * getWidth(), 0, getWidth() * (i + 1), getHeight());
//
//                if(currentId == 0){
//
//                    if(i == 0){
//                        view.layout(0, 0,width, height);
//                    }else if(i == 1){
//                        view.layout(width, 0,width, height);
//                    }else{
//                        view.layout(-width, 0,width, height);
//                    }
//
//                }else if(i == 1){
//                    view.layout(i*width, 0,(i+1)*width, height);
////                    if(i == 0){
////                        view.layout(0, 0,width, height);
////                    }else if(i == 1){
////                        view.layout(width, 0,width, height);
////                    }else{
////                        view.layout(-width, 0,width, height);
////                    }
//                }else{
//                    if(i == 0){
//                        view.layout(0, 0,width, height);
//                    }else if(i == 1){
//                        view.layout(width, 0,width, height);
//                    }else{
//                        view.layout(-width, 0,width, height);
//                    }
//                }

//            int position = getScrollX() % getWidth();
//
//            if (position == 0) {
//                removeViewAt();
//            }
        }
    }
}
