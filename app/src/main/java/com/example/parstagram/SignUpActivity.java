package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Sign Up
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupUser(etEmail.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void signupUser(String email, String username, String password) {
        Log.i(TAG, "Attempting to sign up user "+username);

        // Create a new ParseUser
        ParseUser newUser = new ParseUser();

        // Set core properties
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        // Invoke signUpInBackground
        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(SignUpActivity.this, "Invalid Username and/or Password", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Issue with login", e);
                    return;
                } else {
                    goMainActivity();
                }
            }
        });
    }

    // Takes user to main activity
    private void goMainActivity() {
        Intent intent = new Intent(this, TimelineActivity.class);
        this.startActivity(intent);
        finish();
    }
}