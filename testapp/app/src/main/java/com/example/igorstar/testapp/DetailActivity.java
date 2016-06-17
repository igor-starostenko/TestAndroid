package com.example.igorstar.testapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailActivity extends SignUpActivity {

    private TextView uName;
    private TextView pWord;

    private static final String TAG = "DetailActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        String userName = extras.getString("name");
        String password = extras.getString("pass");

        Log.e(TAG, "userName: "+userName);
        Log.e(TAG, "password: "+password);
        uName = (TextView) findViewById(R.id.uname);
        uName.setText("Hello "+userName+"!");
        pWord = (TextView) findViewById(R.id.password);
        pWord.setText(password);


    }



}
