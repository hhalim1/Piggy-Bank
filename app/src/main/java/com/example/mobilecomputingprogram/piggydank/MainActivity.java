package com.example.mobilecomputingprogram.piggydank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity {
    private double myCash;
    private SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);


        if (myPrefs.getFloat("cash", 0) < 0) {
            myCash = 0;

            SharedPreferences.Editor editor = myPrefs.edit();
            editor.putFloat("cash", 0);
            editor.commit();
        }

        Button goButton = (Button) findViewById(R.id.go_btn);
        goButton.setOnClickListener(begin);

        TextView cashMain = (TextView) findViewById(R.id.cash_main);
        cashMain.setText("$" + String.format("%.02f", myCash));
    }

    protected void onStart() {
        super.onStart();

        myCash = myPrefs.getFloat("cash", 0);
        TextView cashMain = (TextView) findViewById(R.id.cash_main);
        cashMain.setText("$" + String.format("%.02f", myCash));
    }

    protected void onResume() {
        super.onResume();

        myCash = myPrefs.getFloat("cash", 0);
        TextView cashMain = (TextView) findViewById(R.id.cash_main);
        cashMain.setText("$" + String.format("%.02f", myCash));
    }

    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putFloat("cash", (float) myCash);
        editor.commit();
    }

    private final View.OnClickListener begin = new View.OnClickListener() {
        public void onClick(View v) {
            Intent leave = new Intent(MainActivity.this, TransactionActivity.class);
            startActivity(leave);
        }
    };
}
