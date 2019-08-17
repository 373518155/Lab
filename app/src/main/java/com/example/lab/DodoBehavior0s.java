package com.example.lab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class DodoBehavior0s extends CoordinatorLayout.Behavior<Button> {
    public DodoBehavior0s(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Button child, @NonNull View dependency) {
        // 如果dependency是DodoMoveView類型，就依賴
        return dependency instanceof DodoMoveView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull Button child, @NonNull View dependency) {
        int x = 0;
        int y = (int) dependency.getY();

        setPosition(child, x, y);
        return true;
    }

    private void setPosition(View v, int x, int y) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;

        v.setLayoutParams(layoutParams);
    }
}
