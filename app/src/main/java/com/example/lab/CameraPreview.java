package com.example.lab;

import android.content.Context;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    boolean oneShot;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();

            Camera.Parameters parameters = mCamera.getParameters();
            int format = parameters.getPreviewFormat();
            int previewFrameRate = parameters.getPreviewFrameRate();
            Camera.Size previewSize = parameters.getPreviewSize();
            int pictureFormat = parameters.getPictureFormat();
            Camera.Size pictureSize = parameters.getPictureSize();

            SLog.info("format[%d], previewFrameRate[%d], previewSize[%d,%d], pictureFormat[%d], pictureSize[%d,%d]",
                    format, previewFrameRate, previewSize.width, previewSize.height, pictureFormat, pictureSize.width, pictureSize.height);

            List<Integer> formatList = parameters.getSupportedPreviewFormats();
            for (Integer fmt : formatList) {
                SLog.info("SupportedPreviewFormat[%d]", fmt);
            }

            formatList = parameters.getSupportedPictureFormats();
            for (Integer fmt : formatList) {
                SLog.info("SupportedPictureFormat[%d]", fmt);
            }

            List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();
            for (Camera.Size size : sizeList) {
                SLog.info("SupportedPreviewSize[%d,%d]", size.width, size.height);
            }

            sizeList = parameters.getSupportedPictureSizes();
            for (Camera.Size size : sizeList) {
                SLog.info("SupportedPictureSize[%d,%d]", size.width, size.height);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.
        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (Exception e) {

        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
        if (mCamera != null) {
            mCamera.release();
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        // https://blog.csdn.net/tanmengwen/article/details/41412425
        // Android -- 将NV21图像保存成JPEG
        long threadId = Thread.currentThread().getId();
        // SLog.info("onPreviewFrame, threadId[%d], length[%d]", threadId, data.length);
        if (oneShot) {
            String absoluteFilePath = Environment.getExternalStorageDirectory() + "/1/" + System.currentTimeMillis() + ".nv21";
            SLog.info("absoluteFilePath[%s]", absoluteFilePath);
            File file = new File(absoluteFilePath);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            oneShot = false;
        }
    }


    public void setOneShot(boolean oneShot) {
        this.oneShot = oneShot;
    }
}
