package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText m_txtPassword;
    private EditText m_txtUsername;
    private Button m_btnSignup;
    private Button m_btnLogin;
    private String username;
    private String password;

    private  HashMap<String, String> accounts;  //data that holds username and password
    private int SIGNUP_ACCOUNT = 1; //request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating control objects
        m_btnSignup = findViewById(R.id.btnSignup);
        m_btnLogin = findViewById(R.id.btnLogin);
        m_txtPassword = findViewById(R.id.txtPassword);
        m_txtUsername = findViewById(R.id.txtUsername);

        accounts = new HashMap<>();

        //Listener for Login Button
        m_btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username = m_txtUsername.getText().toString();
                password = m_txtPassword.getText().toString();

                //Checks if username and password is in the data
                if (accounts.containsKey(username) && accounts.containsValue(password)) {
                    Intent intent = new Intent(getApplicationContext(), Welcome.class);
                    intent.putExtra("WelcomeMessage", "Welcome " + username + "!");
                    startActivity(intent);
                }
                else
                {
                    m_txtPassword.setText("");
                    Toast.makeText(getApplicationContext(), "Incorrect username/password.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Listener for Sign up Button
        m_btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                intent.putExtra("getAccounts", accounts);
                startActivityForResult(intent, SIGNUP_ACCOUNT);
            }
        });
    }

    //https://stackoverflow.com/questions/9268153/what-is-the-meaning-of-requestcode-in-startactivityforresult/14148838
    //Credited to Josue Crandall
    //Retrieve results from other activities
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGNUP_ACCOUNT && resultCode == RESULT_OK) {
            accounts = (HashMap<String, String>) data.getSerializableExtra("addAccount");
            username = data.getStringExtra("loginUN");
            m_txtUsername.setText(username);
            m_txtPassword.setText("");
        }
    }

}
