package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    private EditText m_txtSignupUN;
    private EditText m_txtSignupPW;
    private EditText m_txtSignupRetypePW;
    private EditText m_txtSignupEmail;
    private EditText m_txtSignupPhone;
    private Button m_btnSignMeUp;
    private HashMap<String, String> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        m_txtSignupUN = findViewById(R.id.txtSignupUN);
        m_txtSignupPW = findViewById(R.id.txtSignupPW);
        m_txtSignupRetypePW = findViewById(R.id.txtSignupRetypePW);
        m_txtSignupEmail = findViewById(R.id.txtSignupEmail);
        m_txtSignupPhone = findViewById(R.id.txtSignupPhone);
        m_btnSignMeUp = findViewById(R.id.btnSignMeUp);

        //Get accounts from main activity.
        accounts = (HashMap<String, String>)getIntent().getSerializableExtra("getAccounts");
        //Listener for Sign Me Up Button
        m_btnSignMeUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = m_txtSignupUN.getText().toString();
                String password = m_txtSignupPW.getText().toString();
                String retypePW = m_txtSignupRetypePW.getText().toString();
                String email = m_txtSignupEmail.getText().toString();
                String phone = m_txtSignupPhone.getText().toString();

                //Check if all fields are filled
                if(username.isEmpty() || password.isEmpty() || retypePW.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields must be filled.", Toast.LENGTH_LONG).show();
                }
                //Check for unique username
                else if(accounts.containsKey(username)) {
                    Toast.makeText(getApplicationContext(), "Username is taken.", Toast.LENGTH_LONG).show();
                }
                //Check pw and retypePW
                else if((password.compareTo(retypePW) != 0)) {
                    Toast.makeText(getApplicationContext(), "Password and Retype Password must be the same.", Toast.LENGTH_LONG).show();
                }
                //Check email format (Credited to Josue Crandall)
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "Invalid email.", Toast.LENGTH_LONG).show();
                }
                //Check phone format
                else if(!Patterns.PHONE.matcher(phone).matches()) {
                    Toast.makeText(getApplicationContext(), "Invalid phone number.", Toast.LENGTH_LONG).show();
                }
                //If everything passes, add account to data and send user to login page.
                //Credited to Josue Crandall
                else {
                    accounts.put(username, password);

                    Intent intent = new Intent();
                    intent.putExtra("addAccount", accounts);    //Return accounts
                    intent.putExtra("loginUN", username);       //Return new username
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });


    }
}