package com.example.lab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout llContainer;
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SLog.info("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtil.requestStoragePermission(this);

        rvList = findViewById(R.id.rv_list);
        llContainer = findViewById(R.id.ll_container);

        llContainer.post(new Runnable() {
            @Override
            public void run() {
                int width = llContainer.getWidth();
                int height = llContainer.getHeight();
                SLog.info("width[%d], height[%d]", width, height);

                ViewGroup.LayoutParams layoutParams = rvList.getLayoutParams();
                layoutParams.height = height;
                rvList.setLayoutParams(layoutParams);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }
}
