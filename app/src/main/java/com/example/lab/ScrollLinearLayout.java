package com.example.lab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class ScrollLinearLayout extends LinearLayout {
    Scroller mScroller;
    public ScrollLinearLayout(Context context) {
        this(context, null);
    }

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        SLog.info("HERE");
    }

    public void cScroll() {
        mScroller.startScroll(0, getScrollY(), 0, -100, 2000);
        invalidate();
    }
}
