package com.example.textviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyTextView muTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        muTextView = (MyTextView) findViewById(R.id.muTextView);

        muTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muTextView.setChatMessage(
                        "重大新闻：","这是一条新闻，这是一条新闻，这是一条新闻，这是一条新闻，" +
                                "这是一条新闻，这是一条新闻，这是一条新闻，这是一条新闻，这是一条新闻，",
                        Color.BLUE,Color.BLACK);
            }
        });

    }
}
