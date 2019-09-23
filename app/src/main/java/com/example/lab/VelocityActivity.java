package com.example.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.OverScroller;
import android.widget.Scroller;

public class VelocityActivity extends AppCompatActivity implements View.OnClickListener {
    ScrollLinearLayout scrollLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);
        scrollLinearLayout = findViewById(R.id.scroll_linear_layout);
        findViewById(R.id.btn_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_test) {
            scrollLinearLayout.cScroll();
        }
    }
}
