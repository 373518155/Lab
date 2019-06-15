package com.example.lab;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MyThread extends Thread {
    private SurfaceHolder surfaceHolder;

    boolean finish;

    public MyThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        finish = false;
    }

    @Override
    public void run() {
        Canvas canvas = null;
        try {
            while (!finish) {
                // 獲取Canvas對象，并鎖定之
                canvas = surfaceHolder.lockCanvas();
                if (canvas == null) {
                    continue;
                }

                Paint paint = new Paint();
                paint.setColor(Color.parseColor("#FF0000"));
                canvas.drawText("hello", 0, 0, paint);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void terminate() {
        finish = true;
    }
}
