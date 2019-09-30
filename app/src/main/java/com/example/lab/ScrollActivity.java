package com.example.lab;

import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity implements View.OnClickListener {
    CustomScrollView nsvContainer;
    MaxHeightRecyclerView recyclerView;


    int currentValue;

    TextAdapter adapter;
    List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        initData();

        findViewById(R.id.btn_test).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);

        nsvContainer = findViewById(R.id.nsv_container);
        SLog.info("nsvContainer[%s]", nsvContainer instanceof NestedScrollingParent);
        SLog.info("nsvContainer[%s]", nsvContainer instanceof NestedScrollingParent2);
        recyclerView = findViewById(R.id.recycler_view);
        nsvContainer.post(new Runnable() {
            @Override
            public void run() {
                int height = nsvContainer.getHeight();
                recyclerView.setMaxHeight(height);

                SLog.info("height[%d], rawY[%d]", height, Util.getYOnScreen(nsvContainer));
                nsvContainer.setRefView(recyclerView);
                nsvContainer.setyLocation(Util.getYOnScreen(nsvContainer));

                populateData();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            stringList.add(String.valueOf(i));
            currentValue = i;
        }
    }

    private void populateData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TextAdapter(R.layout.text_item, stringList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_test) {
            SLog.info("here");
            nsvContainer.scrollBy(0, 20);
        } else if (id == R.id.btn_add) {
            currentValue++;
            stringList.add(String.valueOf(currentValue));
            adapter.setNewData(stringList);
        }
    }


}
