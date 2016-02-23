package com.example.mobilecomputingprogram.piggydank;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Formatter;
import java.util.Stack;

public class TransactionActivity extends AppCompatActivity {
    private SharedPreferences myPrefs;
    private Stack<Double> actions;
    private double myCash;
    private double selection;

    private final double PENNY = 0.01;
    private final double NICKEL = 0.05;
    private final double DIME = 0.10;
    private final double QUART = 0.25;
    private final double DOLL = 1;
    private final double FIVE = 5;
    private final double TEN = 10;
    private final double TWENT = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        myCash = myPrefs.getFloat("cash", 0);

        TextView cashTrans = (TextView) findViewById(R.id.cash_transaction);
        cashTrans.setText("$" + String.format("%.02f", myCash));

        actions = new Stack<>();

        Button wdButton = (Button) findViewById(R.id.withdraw_btn);
        wdButton.setOnClickListener(wdListener);

        Button dpButton = (Button) findViewById(R.id.deposit_btn);
        dpButton.setOnClickListener(dpListener);

        Button undoButton = (Button) findViewById(R.id.undo_button);
        undoButton.setOnClickListener(undo);

        ImageButton pennyBtn = (ImageButton) findViewById(R.id.penny_img);
        pennyBtn.setOnClickListener(pListener);

        ImageButton nickelBtn = (ImageButton) findViewById(R.id.nickle_img);
        nickelBtn.setOnClickListener(nlistener);

        ImageButton dimeBtn = (ImageButton) findViewById(R.id.dime_img);
        dimeBtn.setOnClickListener(dmlistener);

        ImageButton quarterBtn = (ImageButton) findViewById(R.id.quarter_img);
        quarterBtn.setOnClickListener(qlistener);

        ImageButton dollarBtn = (ImageButton) findViewById(R.id.dollar_img);
        dollarBtn.setOnClickListener(dlistener);

        ImageButton fiveBtn = (ImageButton) findViewById(R.id.five_img);
        fiveBtn.setOnClickListener(fvlistener);

        ImageButton tenBtn = (ImageButton) findViewById(R.id.ten_img);
        tenBtn.setOnClickListener(tenlistener);

        ImageButton twentyBtn = (ImageButton) findViewById(R.id.twenty_img);
        twentyBtn.setOnClickListener(twlistener);
    }

    private final View.OnClickListener pListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actions.push(PENNY);
            updateSelection(PENNY, false);
        }
    };

    private final View.OnClickListener nlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actions.push(NICKEL);
            updateSelection(NICKEL, false);
        }
    };

    private final View.OnClickListener dmlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actions.push(DIME);
            updateSelection(DIME, false);
        }
    };

    private final View.OnClickListener qlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actions.push(QUART);
            updateSelection(QUART, false);
        }
    };

    private final View.OnClickListener dlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actions.push(DOLL);
            updateSelection(DOLL, false);
        }
    };

    private final View.OnClickListener fvlistener = new View.OnClickListener() {
        public void onClick(View v) {
            actions.push(FIVE);
            updateSelection(FIVE, false);
        }
    };

    private final View.OnClickListener tenlistener = new View.OnClickListener() {
        public void onClick(View v) {
            actions.push(TEN);
            updateSelection(TEN, false);
            //print to screen dollars
        }
    };

    private final View.OnClickListener twlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actions.push(TWENT);
            updateSelection(TWENT, false);
            //print to screen dollars
        }
    };

    private void updateSelection(double amount, boolean undid) {
        selection += amount;
        TextView selTrans = (TextView) findViewById(R.id.select_transaction);
        selTrans.setText("$" + String.format("%.02f", selection));

        TextView actTrans = (TextView) findViewById(R.id.action);
        if (undid) {
            actTrans.setText("-" + String.format("%02.02f", amount));
        } else {
            actTrans.setText("+" + String.format("%.02f", amount));
        }

    }

    private final View.OnClickListener wdListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if ((myCash + selection) - ((myCash + selection) % 0.01) >= 0) {
                myCash -= selection;
                TextView cashTrans = (TextView) findViewById(R.id.cash_transaction);
                cashTrans.setText("$" + String.format("%.02f", myCash));

                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putFloat(getString(R.string.preferences), (float) myCash);
                editor.commit();
                //subtract current amount and stuff
            }
            //else shake angrily, etc
        }
    };

    private final View.OnClickListener dpListener = new View.OnClickListener() {
        public void onClick(View v) {
            myCash += selection;
            TextView cashTrans = (TextView) findViewById(R.id.cash_transaction);
            cashTrans.setText("$" + String.format("%.02f", myCash));

            SharedPreferences.Editor editor = myPrefs.edit();
            editor.putFloat(getString(R.string.preferences), (float) myCash);
            editor.commit();
            //add current amount and stuff
        }
    };

    private final View.OnClickListener undo = new View.OnClickListener() {
        public void onClick(View v) {
            if (!actions.isEmpty()) {
                double lastAction = actions.pop();
                updateSelection(-lastAction, true);
            }

        }
    };

}
