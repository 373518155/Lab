package com.example.lab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
    View refView;

    int yLocation;

    public CustomScrollView(Context context) {
        this(context, null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRefView(View refView) {
        this.refView = refView;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        SLog.info("onStartNestedScroll ...");
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
        SLog.info("onNestedScrollAccepted");
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        SLog.info("onNestedPreScroll");
        if (dy == 0) {
            return;
        }

        int refY = Util.getYOnScreen(refView);
        int diff = refY - yLocation;

        consumed[0] = 0;
        if (dy > diff) {
            scrollBy(0, diff);
            consumed[1] = diff;
        } else {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        SLog.info("onNestedScroll");
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
        SLog.info("onStopNestedScroll");
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        boolean result = super.onNestedPreFling(target, velocityX, velocityY);
        SLog.info("onNestedPreFling, result[%s], velocityY[%s]", result, velocityY);
        return result;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        SLog.info("onNestedFling");
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }
}