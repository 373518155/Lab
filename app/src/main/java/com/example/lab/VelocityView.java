package com.example.lab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * 追踪手指滑动的速度
 */
public class VelocityView extends View {
    VelocityTracker velocityTracker;
    public VelocityView(Context context) {
        this(context, null);
    }

    public VelocityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VelocityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
                SLog.info("x[%s], y[%s]", velocityTracker.getXVelocity(), velocityTracker.getYVelocity());
                // 回收
                velocityTracker.recycle();
                break;
            default:
                break;
        }
        return true;
    }
}
