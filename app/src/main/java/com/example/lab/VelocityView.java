package com.example.lab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

/**
 * 追踪手指滑动的速度
 */
public class VelocityView extends View {
    VelocityTracker velocityTracker;
    Scroller mScroller;
    public VelocityView(Context context) {
        this(context, null);
    }

    public VelocityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VelocityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 初始化VelocityTracker
                velocityTracker = VelocityTracker.obtain();
                break;
            case MotionEvent.ACTION_MOVE:
                // 追蹤
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                SLog.info("x[%s], y[%s]", velocityTracker.getXVelocity(), velocityTracker.getYVelocity());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                int yVelocity = (int) velocityTracker.getYVelocity();
                SLog.info("x[%s], y[%s]", velocityTracker.getXVelocity(), yVelocity);
                // 回收
                velocityTracker.recycle();

                if (Math.abs(yVelocity) > 1000) {
                    SLog.info("產生滾動");
                    mScroller.fling(0, getScrollY(), 0, -yVelocity, 0, 0, 0, getHeight());
                    invalidate();
                } else {
                    SLog.info("不 產生滾動");
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        SLog.info("111111");
        if (mScroller.computeScrollOffset()) {
            SLog.info("22222");
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            SLog.info("currX[%d], currY[%d]", currX, currY);
            scrollTo(currX, currY);
            invalidate();
        }
    }
}
