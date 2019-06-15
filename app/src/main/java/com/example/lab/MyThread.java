package com.example.lab;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;

public class MyThread extends Thread {
    private SurfaceHolder surfaceHolder;

    // 是否結束的標志位
    boolean finish;

    public MyThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        finish = false;
    }

    @Override
    public void run() {
        try {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#FF0000"));
            paint.setTextSize(40);

            while (!finish) {
                SLog.info("looping");
                // 獲取Canvas對象，并鎖定之
                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas == null) {
                    continue;
                }

                // 繪制時，需要先清除畫布，否則上次繪制的還殘留在這里
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

                // 繪制當前時間戳
                long timestamp = System.currentTimeMillis();
                canvas.drawText(String.valueOf(timestamp), 150, 150, paint);

                // 解除鎖定，并提交修改的內容
                surfaceHolder.unlockCanvasAndPost(canvas);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void terminate() {
        finish = true;
    }
}
