package com.example.nyobasebelumfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    DBHelper dbHelper;
    TextInputEditText username, password;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.btnregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Registration.class);
                startActivity(intent);
                finish();
            }
        });

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        dbHelper = new DBHelper(this);
        login = findViewById(R.id.buttonSignin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LoginUsername = username.getText().toString().trim();
                String LoginPassword = password.getText().toString().trim();

                Boolean check = dbHelper.checkUser(LoginUsername, LoginPassword);
                if (check == true){
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, AfterLogReg.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}