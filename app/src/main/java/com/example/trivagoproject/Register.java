package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    TrivagoDBHelper myDb ;
    TextInputLayout UserNameReg;
    TextInputLayout Email;
    TextInputLayout PassReg;
    TextInputLayout ConPassReg;
    Button Btn;
    String use,eml,Pass,ConPass;
    Boolean a,b,c,d;
    boolean S ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new TrivagoDBHelper(this);

        UserNameReg = (TextInputLayout) findViewById(R.id.Username);
        Email = (TextInputLayout) findViewById(R.id.Email);
        PassReg = (TextInputLayout) findViewById(R.id.Password);
        ConPassReg = (TextInputLayout) findViewById(R.id.conPassword);
        Btn = (Button) findViewById(R.id.button);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = false;
                b = false;
                c = false;
                d = false;

                //getting texts
                eml = Email.getEditText().getText().toString();
                use = UserNameReg.getEditText().getText().toString();
                Pass = PassReg.getEditText().getText().toString();
                ConPass = ConPassReg.getEditText().getText().toString();

                // email not empty
                if (eml.isEmpty()) {
                    Email.setError("Please enter email");
                } else {
                    Email.setError(null);
                    b = true;
                }


                //User is not used before and not Empty
                S = myDb.UserName_Existed(use);
                if (use.isEmpty()) {
                    UserNameReg.setError("Please enter username");
                } else if (S == false) {
                    UserNameReg.setError("Username is already used");
                } else {
                    UserNameReg.setError(null);
                    a = true;
                }


                // pass not empty and not less than 6
                if (Pass.isEmpty()) {
                    PassReg.setError("Please enter password");
                } else if (Pass.length() < 6) {
                    PassReg.setError("Password is too short");
                } else {
                    PassReg.setError(null);
                    c = true;
                }


                //confirm pass is equals
                if (!(Pass.equals(ConPass))) {
                    ConPassReg.setError("Passwords don't match");
                } else {
                    ConPassReg.setError(null);
                    d = true;
                }

                if (a && b && c && d) {

                    //***** USER****//
                    User NormalUser = new NormalUser(use, eml, Pass);
                    myDb.AddNewUser(NormalUser);
                    Toast.makeText(getApplicationContext(), "Registration Completed", Toast.LENGTH_LONG).show();
                    Intent login_page=new Intent(getApplicationContext(),LogIn.class);
                    startActivity(login_page);
                }

            }
        });


    }
    }


