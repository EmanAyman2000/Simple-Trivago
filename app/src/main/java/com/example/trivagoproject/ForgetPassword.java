package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ForgetPassword extends AppCompatActivity {
    TextInputLayout username;
    Button reset;
    TrivagoDBHelper dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        username=(TextInputLayout) findViewById(R.id.et_userName);
        reset=(Button)findViewById(R.id.btn_reset);
        dp=new TrivagoDBHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getEditText().getText().toString();
                if(user.isEmpty()){
                    username.setError("Please enter user name");
                }
                if(dp.checkBYName(user)==true&&!user.isEmpty()){
                    username.setError(null);
                    Intent intent=new Intent(getApplicationContext(), ResetPassword.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }
                else if(dp.checkBYName(user)==false&&!user.isEmpty()){
                    username.setError(null);
                    Toast.makeText(getApplicationContext(),"User does not exist", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}