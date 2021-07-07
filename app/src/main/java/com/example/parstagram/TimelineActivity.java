package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class TimelineActivity extends AppCompatActivity {

    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        btnLogOut = findViewById(R.id.btnLogOut);

        // Log out
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent intent = new Intent(TimelineActivity.this, LoginActivity.class);
                TimelineActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}