package com.example.parstagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parstagram.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = ParseApplication.class.getSimpleName();
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Takes user directly to main activity if already logged in
        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Log In Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.i(TAG, "onClick signup button");
                 goSignUpActivity();
             }
         });
    }

    // Verifies login and takes user to main activity
    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user "+username);

        // Call LogInBackground to ensure it runs in background thread and not default on main UI thread
        // Use callback functions
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Toast.makeText(LoginActivity.this, "Invalid Username and/or Password", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        this.startActivity(intent);
        finish();
    }

    // Takes user to main activity
    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();
    }

    // TODO
//    @Override
//    public void onBackPressed() {
////        if (ParseUser.getCurrentUser() == null) {
////
////        }
//        super.onBackPressed();
//    }
}