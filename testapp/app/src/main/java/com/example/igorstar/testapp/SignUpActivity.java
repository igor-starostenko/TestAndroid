package com.example.igorstar.testapp;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;


/**
 * A login screen that offers login via username/password.
 */

public class SignUpActivity extends MainActivity{

    private static final String TAG = "SignUpActivity";

    // UI references.
    private AutoCompleteTextView mUNView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    protected String userName;
    protected String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUNView = (AutoCompleteTextView) findViewById(R.id.userName);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void submit(View view) {
        attemptLogin();
        if (userName.isEmpty()||password.isEmpty()){
            return;
        }
        Intent intent = new Intent(SignUpActivity.this, DetailActivity.class);
        intent.putExtra("name",userName);
        intent.putExtra("pass",password);
        startActivity(intent);
        Log.e(TAG, "userName: "+userName);
        Log.e(TAG, "password: "+password);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid userName, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        // Reset errors.
        mUNView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        userName = mUNView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid userName.
        if (TextUtils.isEmpty(userName)) {
            mUNView.setError(getString(R.string.error_field_required));
            focusView = mUNView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Perform the user login attempt.
        }
    }
}

