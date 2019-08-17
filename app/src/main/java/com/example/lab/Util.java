package com.example.lab;

import android.content.Context;
import android.view.View;

public class Util {
    public static int dip2px(Context context, float dipValue) {
        float m = context.getResources().getDisplayMetrics().density;
        int result = (int) (dipValue * m + 0.5f);
        SLog.info("result[%d]", result);

        return result;
    }

    public static int getYOnScreen(View view) {
        int[] location = new int[2] ;
        view.getLocationOnScreen(location);
        return location[1];
    }
}
