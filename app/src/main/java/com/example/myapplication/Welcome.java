package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {
    // Member variable for holding a textview object
    private TextView m_txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Textview object assignment
        m_txtMessage = findViewById(R.id.txtMessage);

        // Reading the value of the intent sent here
        Intent intent = getIntent();
        String message = intent.getStringExtra("WelcomeMessage");
        // Assigning the value to the TextView object
        m_txtMessage.setText(message);

    }
}
