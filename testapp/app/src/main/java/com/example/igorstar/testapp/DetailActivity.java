package com.example.igorstar.testapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

public class DetailActivity extends SignUpActivity {

    private TextView uName;
    private TextView pWord;
    private PackageManager pm;
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    public void openGeolocation(View view) {
        Intent intent = new Intent(DetailActivity.this, GetGeoActivity.class);
        intent.putExtra("name",userName);
        intent.putExtra("pass",password);
        startActivity(intent);
    }

    public void openInstalledApps(View view) {
        Intent intent = new Intent(DetailActivity.this, AppListActivity.class);
        startActivity(intent);
    }


}
