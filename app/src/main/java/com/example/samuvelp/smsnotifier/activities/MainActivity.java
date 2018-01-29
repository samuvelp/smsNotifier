package com.example.samuvelp.smsnotifier.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;


import com.example.samuvelp.smsnotifier.R;

public class MainActivity extends AppCompatActivity {
    EditText userPhoneNumber;
    Button saveButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
        String phoneNumber = sharedPreferences.getString("phoneNumber", null);
        if (phoneNumber != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("phoneNumber", userPhoneNumber.getText().toString().trim());
                editor.apply();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    private void init() {
        userPhoneNumber = findViewById(R.id.userPhoneNumber);
        saveButton = findViewById(R.id.saveButton);
        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
    }

}
