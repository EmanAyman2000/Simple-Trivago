package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ResetPassword extends AppCompatActivity {
    TextInputLayout resetpassword;
    TextInputLayout Retypepassword;
    Button confirm;
    TrivagoDBHelper dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        resetpassword=(TextInputLayout) findViewById(R.id.passwordReset);
        Retypepassword=(TextInputLayout) findViewById(R.id.repasswordReset);
        confirm=(Button)findViewById(R.id.btnConfirm);
        dp=new TrivagoDBHelper(this);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetpassword.setError(null);
                Retypepassword.setError(null);
                Intent intent=getIntent();
                String user = intent.getStringExtra("username");
                String password = resetpassword.getEditText().getText().toString();
                String repass = Retypepassword.getEditText().getText().toString();
                if(password.isEmpty()){
                    resetpassword.setError(null);
                    resetpassword.setError("Please enter password");
                }
                if(repass.isEmpty()){
                    Retypepassword.setError(null);
                    Retypepassword.setError("Please enter re-type password");
                }
                if (password.equals(repass)&&!password.isEmpty()&&!repass.isEmpty()) {
                    if (dp.checkBYName(user)) {
                        resetpassword.setError(null);
                        Retypepassword.setError(null);
                        dp.update(user, password);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Password updated successfully", Toast.LENGTH_LONG).show();
                    } else {
                        resetpassword.setError(null);
                        Retypepassword.setError(null);
                        Toast.makeText(getApplicationContext(), "Password Not Updated", Toast.LENGTH_LONG).show();
                    }
                }else if(!password.equals(repass)&&!password.isEmpty()&&!repass.isEmpty()){
                    resetpassword.setError(null);
                    Retypepassword.setError("Password Not Matching");
                    Toast.makeText(getApplicationContext(), "Password Not Matching", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}