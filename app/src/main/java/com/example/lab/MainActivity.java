package com.example.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.security.Permission;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SLog.info("onCreate");

        PermissionUtil.requestStoragePermission(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }
}
