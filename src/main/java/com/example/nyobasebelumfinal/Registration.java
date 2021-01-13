package com.example.nyobasebelumfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText email, username, password, conpassword;
    Button btnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DBHelper(this);
        email = findViewById(R.id.emailRegis);
        username = findViewById(R.id.usernameRegis);
        password = findViewById(R.id.passworRegis);
        conpassword = findViewById(R.id.passwordConfrimationRegis);
        btnRegister = findViewById(R.id.buttonSignup);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailReg = email.getText().toString();
                String usernameReg = username.getText().toString();
                String passwordReg = password.getText().toString();
                String conpasswordReg = conpassword.getText().toString();
                ContentValues values = new ContentValues();

                if (!passwordReg.equals(conpasswordReg)){
                    Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_SHORT).show();
                }else if (passwordReg.equals("") || usernameReg.equals("")){
                    Toast.makeText(getApplicationContext(), "Username or Password cannot empty", Toast.LENGTH_SHORT).show();
                }else{
                    values.put(DBHelper.row_email, emailReg);
                    values.put(DBHelper.row_username, usernameReg);
                    values.put(DBHelper.row_password, passwordReg);
                    dbHelper.insertData(values);
                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registration.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}