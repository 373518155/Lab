package com.example.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    ScrollTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        findViewById(R.id.btn_test).setOnClickListener(this);

        textView = findViewById(R.id.text_view);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            sb.append(i + "\n");
        }

        textView.setText(sb.toString());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_test) {
            textView.scrollBy(0, 100);
        }
    }
}
