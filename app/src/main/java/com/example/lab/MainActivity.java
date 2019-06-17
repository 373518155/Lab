package com.example.lab;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.yanzhenjie.permission.Permission;

/**
 * https://developer.android.google.cn/guide/topics/media/camera.html
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Camera mCamera;
    CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SLog.info("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtil.requestStoragePermission(this);
        PermissionUtil.requestStoragePermission(this);

        findViewById(R.id.btn_get_number_of_cameras).setOnClickListener(this);
        findViewById(R.id.btn_open_camera).setOnClickListener(this);

        PermissionUtil.actionWithPermission(this, Permission.Group.CAMERA, "使用本APP需要授予", new CommonCallback() {
            @Override
            public String onSuccess(@Nullable String data) {
                mCamera = getCameraInstance();
                mPreview = new CameraPreview(MainActivity.this, mCamera);
                FrameLayout preview = findViewById(R.id.camera_preview);
                preview.addView(mPreview);
                return null;
            }

            @Override
            public String onFailure(@Nullable String data) {
                return null;
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_get_number_of_cameras) {
            int numberOfCameras = Camera.getNumberOfCameras();
            SLog.info("numberOfCameras[%d]", numberOfCameras);
        } else if (id == R.id.btn_open_camera) {
            if (mCamera != null) {
                return;
            }
            mCamera = getCameraInstance();

            // 獲取參數
            Camera.Parameters parameters = mCamera.getParameters();

            // 獲取信息
            Camera.CameraInfo cameraInfo = null;
            Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, cameraInfo);
        }
    }


    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}
