package com.example.igorstar.testapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

import com.example.testlibrary.VigenereCipher;

public class DetailActivity extends SignUpActivity {

    private TextView uName;
    private TextView pWord;
    private PackageManager pm;
    private boolean decrypted;
    private boolean firstTime;
    private String enc;
    private String dec;
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        String user = extras.getString("name");
        String pass = extras.getString("pass");
        decrypted = true;
        firstTime = true;

        Log.e(TAG, "userName: "+user);
        Log.e(TAG, "password: "+pass);
        uName = (TextView) findViewById(R.id.uname);
        uName.setText("Hello "+user+"!");
        pWord = (TextView) findViewById(R.id.password);
        pWord.setText(pass);
    }

    public void openGeolocation(View view) {
        Intent intent = new Intent(DetailActivity.this, GetGeoActivity.class);
        intent.putExtra("name",SignUpActivity.user);
        intent.putExtra("pass",SignUpActivity.pass);
        startActivity(intent);
    }

    public void openInstalledApps(View view) {
        Intent intent = new Intent(DetailActivity.this, AppListActivity.class);
        startActivity(intent);
    }

    public void encrypt(View view) {
        Button encrypt = (Button)findViewById(R.id.encrypt);
        //Make a check if the password is encrypted
        if (decrypted == true) {
            if (firstTime == true){
                enc = VigenereCipher.encrypt(pass, 2);
                firstTime = false;
            }else {
                enc = VigenereCipher.encrypt(dec, 2);
            }
            pWord.setText(enc);
            encrypt.setText("Decrypt");
            decrypted = false;
        } else {
            dec = VigenereCipher.decrypt(enc, 2);
            pWord.setText(dec);
            encrypt.setText("Encrypt");
            decrypted = true;
        }
    }
}
