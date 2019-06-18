package com.example.lab;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * https://developer.android.google.cn/guide/topics/media/camera.html
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Camera mCamera;
    CameraPreview mPreview;
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            String absoluteFilePath = Environment.getExternalStorageDirectory() + "/1/" + System.currentTimeMillis() + ".jpg";
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
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SLog.info("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtil.requestStoragePermission(this);
        PermissionUtil.requestStoragePermission(this);

        findViewById(R.id.btn_get_number_of_cameras).setOnClickListener(this);
        findViewById(R.id.btn_capture).setOnClickListener(this);

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
        } else if (id == R.id.btn_capture) {
            mCamera.takePicture(null, null, pictureCallback);
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
