package com.example.khaddamuktabharat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ContactUS extends AppCompatActivity {
TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        txt=(TextView) findViewById(R.id.txt);
        txt.setText("Technical Person:ABC XYZ\nContact No-:0000000000\nEmail ID:-ABC@gmail.com\n");
    }
}