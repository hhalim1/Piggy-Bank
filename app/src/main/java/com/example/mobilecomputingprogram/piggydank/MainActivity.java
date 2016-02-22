package com.example.mobilecomputingprogram.piggydank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private double myCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goButton = (Button) findViewById(R.id.go_btn);
        goButton.setOnClickListener(begin);
    }

    private final View.OnClickListener begin = new View.OnClickListener() {
        public void onClick(View v) {
            //go to transition activity
        }
    };
}
