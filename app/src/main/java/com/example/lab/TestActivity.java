package com.example.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    MaxHeightRecyclerView rvList;
    List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        for (int i = 0; i < 2; i++) {
            stringList.add(String.valueOf(i));
        }

        rvList = findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        TestAdapter adapter = new TestAdapter(R.layout.test_list_item, stringList);
        rvList.setAdapter(adapter);
        adapter.setNewData(stringList);

        findViewById(R.id.btn_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_test) {
            rvList.setMaxHeight(rvList.getHeight() + Util.dip2px(this, 20));
        }
    }
}
