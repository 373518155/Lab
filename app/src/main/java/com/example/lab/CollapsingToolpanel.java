package com.example.lab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * CollapsingToolpanel 包含2个子View
 * 第1个为ToolContainer，是一个RelativeLayout, 高度设置为固定高度，ToolContainer的高度必须等于Toolbar的高度
 * 第2个为可滚动的View，我们称为ScrollView，比如RecyclerView、NestedScrollView等实现了NestedScrollingChild接口的类，其高度设为MATCH_PARENT
 *
 * 其中ToolContainer又包含2部分
 * 第1部分为  Toolbar, Toolbar与ToolContainer的底部对齐
 * 第2部分为  Toolpanel，Toolpanel的高度与ToolContainer的高度相等
 * Toolpanel的高度大于Toolbar的高度
 * 这两部分互斥显示，如果想要显示其中一个，则把另一个的visibility设置为invisible(不能设置为gone，因为gone的话，获取不了宽高)
 */
public class CollapsingToolpanel extends LinearLayout {
    public CollapsingToolpanel(Context context) {
        this(context, null);
    }

    public CollapsingToolpanel(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsingToolpanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);
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

        // dy > 0 表示子View向上滑动
        int scrollY = getScrollY();  // Parent滑动了多少，scrollY > 0 表示向上滑动

        SLog.info("dy[%d], scrollY[%d]", dy, scrollY);

        scrollBy(0, dy);
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

