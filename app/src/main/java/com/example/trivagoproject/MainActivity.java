package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TrivagoDBHelper myDb;

    private com.felipecsl.gifimageview.library.GifImageView GifImageView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if(firstStart) {
           createAdmin();
        }

        GifImageView =(GifImageView)findViewById(R.id.gifImageView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(progressBar.VISIBLE);
        try {
            InputStream inputStream = getAssets().open("trivago1.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            GifImageView.setBytes(bytes);
            GifImageView.startAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.startActivity(new Intent(MainActivity.this,LogIn.class));
                MainActivity.this.finish();
            }
        },4000);


        // TrivagoDBHelper t = new TrivagoDBHelper(this);
       //   Intent i = new Intent(MainActivity.this  , LogIn.class);
       //   startActivity(i);
    }


    void createAdmin() {
        //creating admin or admins
        myDb = new TrivagoDBHelper(this);
        User admin = new Admin("admin", "admin", "admin");
        myDb.AddNewUser(admin);

        //sitting the preferences to false
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

    }
}