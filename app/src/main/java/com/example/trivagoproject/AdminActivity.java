package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnAddHotel = (Button) findViewById(R.id.addHotel_btn);
        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this , AddActivity.class);
                startActivity(i);
            }
        });
        Button btnAddRoom = (Button) findViewById(R.id.addRoom_btn);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this , AddRoomActivity.class);
                startActivity(i);
            }
        });

        Button btnShow = (Button) findViewById(R.id.search_btn);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this , ShowAllHotels.class);
                i.putExtra("name" , "0");
                startActivity(i);
            }

        });

        Button btnDelete = (Button) findViewById(R.id.delete_btn);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this , DeleteHotelActivity.class);
                startActivity(i);
            }
        });

        Button btnLogOut = (Button) findViewById(R.id.btnAdminLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this  , LogIn.class);
                startActivity(i);
            }
        });
    }
}