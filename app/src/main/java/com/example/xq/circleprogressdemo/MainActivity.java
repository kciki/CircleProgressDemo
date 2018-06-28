package com.example.xq.circleprogressdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.circleprogressview.CircleProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start(View view) {
        CircleProgressView circleProgressView = findViewById(R.id.circle_progress_view);
        circleProgressView.startAnim();
    }
}
