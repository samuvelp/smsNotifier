package com.example.samuvelp.smsnotifier.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.samuvelp.smsnotifier.R;

public class HomeActivity extends AppCompatActivity {
    TextView switchText;
    Switch switchButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        Boolean switchState = sharedPreferences.getBoolean("isChecked",false);
        if(switchState){
            switchButton.setChecked(true);
            switchText.setText("Turn off SMS notification");
        }else {
            switchButton.setChecked(false);
            switchText.setText("Turn on to receive SMS notification in your browser!");
        }

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switchText.setText("Turn off SMS notification");
                    editor.putBoolean("isChecked",b);
                    editor.apply();
                }else{
                    switchText.setText("Turn on to receive SMS notification in your browser!");
                    editor.putBoolean("isChecked",b);
                    editor.apply();
                }
            }
        });

    }

    private void init() {
        switchText = findViewById(R.id.switchText);
        switchButton = findViewById(R.id.switchButton);
        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}
