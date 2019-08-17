package com.example.lab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ScrollTextView extends android.support.v7.widget.AppCompatTextView {
    float preY;
    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int height = getHeight();
        if (action == MotionEvent.ACTION_DOWN) {

            SLog.info("scrollY[%d], height[%d]", getScrollY(), height);
            preY = event.getRawY();
        } else if (action == MotionEvent.ACTION_MOVE) {
            float y = event.getRawY();

            int diff = (int) (y - preY);
            SLog.info("y[%s], preY[%s], diff[%d]", y, preY, diff);

            int scrollY = getScrollY();
            if (scrollY <= 0 && diff > 0) {
                return true;
            }

            if (scrollY - diff < 0) {
                diff = scrollY;
            }
            scrollBy(0, -diff);

            preY = y;
        }



        boolean result = super.onTouchEvent(event);
        // SLog.info("result[%s]", result);
        return true;
    }
}
