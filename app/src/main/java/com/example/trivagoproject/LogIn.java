package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LogIn extends AppCompatActivity {

    TextInputLayout user;
    TextInputLayout pass;
    Button btnLogin;
    TextView textForget;
    TextView textRegister;
    TrivagoDBHelper trivago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        trivago = new TrivagoDBHelper(this);
        user = (TextInputLayout) findViewById(R.id.text_input_username);
        pass = (TextInputLayout) findViewById(R.id.text_input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        textRegister = (TextView) findViewById(R.id.textRegister);
        textForget = (TextView) findViewById(R.id.tv_forgetPassword);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(LogIn.this,Register.class);
                startActivity(registerIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = user.getEditText().getText().toString().trim();
                String Password = pass.getEditText().getText().toString().trim();
                if (Username.isEmpty()) {
                    user.setError("Please enter user name");
                }
                if (Password.isEmpty()) {
                    pass.setError("Please enter password");
                }
                if (trivago.validation(Username, Password) && !Username.isEmpty() && !Password.isEmpty()) {
                    user.setError(null);
                    pass.setError(null);
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    if (trivago.checkIsAdmin(Username, Password) == 1) {
                        Intent adminIntent=new Intent(LogIn.this,AdminActivity.class);
                        startActivity(adminIntent);
                        user.getEditText().setText("");
                        pass.getEditText().setText("");
                    } else if (trivago.checkIsAdmin(Username, Password) == 0) {
                        Intent userIntent=new Intent(LogIn.this,HotelFilteration.class);
                        startActivity(userIntent);
                        user.getEditText().setText("");
                        pass.getEditText().setText("");
                    }
                } else if (!trivago.validation(Username, Password) && !Username.isEmpty() && !Password.isEmpty()) {
                    user.setError(null);
                    pass.setError(null);
                    Toast.makeText(getApplicationContext(), "Invalid Username or password", Toast.LENGTH_LONG).show();
                }

            }
        });
        textForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(intent);
            }
        });

    }

    }
