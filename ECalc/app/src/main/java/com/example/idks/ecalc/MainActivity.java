package com.example.idks.ecalc;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaCas;
import android.media.session.MediaSessionManager;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addSal = (Button) findViewById(R.id.addSalButton);
        addSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                TextView salaryView = (TextView) findViewById(R.id.salaryField);
                String strSalary = salaryView.getText().toString();
                int finalSalary = Integer.parseInt(strSalary);
                fetchAddSal obj = new fetchAddSal(finalSalary);
                salaryView.setEnabled(false);
                SharedPreferences sharedSalaPref = getSharedPreferences("salInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedSalaPref.edit();
                editor.putInt("finalSalaryValue",finalSalary).commit();

            }
        });

        Button addExp = (Button) findViewById(R.id.addExpButton);
        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int exp;
                TextView expView = (TextView) findViewById(R.id.expField);
                String strExp = expView.getText().toString();
                int finalExpn = Integer.parseInt(strExp);
                fetchAddSal.finalExp(finalExpn);
                SharedPreferences sharedExpPref = getSharedPreferences("salInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedExpPref.edit();
                editor.putInt("finalExpnValue",finalExpn).commit();
                expView.setText("");

            }
        });

        Button calExpPer = (Button) findViewById(R.id.calPer);
        calExpPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                double per = fetchAddSal.calcPer();
                TextView percentageView = (TextView) findViewById(R.id.percentage);
                percentageView.setText(Double.toString(per));

            }
        });



    }

   }
