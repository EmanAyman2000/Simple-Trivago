package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteHotelActivity extends AppCompatActivity {
    Spinner spinner2;
   // ArrayList<String> list;
    TrivagoDBHelper datahelper;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_hotel);
        Button btn =findViewById(R.id.button);
        datahelper=new TrivagoDBHelper(this);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

        Cursor cursor = datahelper.getHotelsNames();
        while(!cursor.isAfterLast())
        {
            adapter.add(cursor.getString(0));
            cursor.moveToNext();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hotelName = spinner2.getSelectedItem().toString();
                Cursor cursor2 = datahelper.getHotelsIDs(hotelName);
                int hotelID = 0 ;
                while(!cursor2.isAfterLast())
                {
                    hotelID=Integer.parseInt(cursor2.getString(0));
                    cursor2.moveToNext();
                }
               if( datahelper.DeleteHotel(hotelID)) {
                   Toast.makeText(DeleteHotelActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                   adapter.remove(hotelName);
               }
               else
                   Toast.makeText(DeleteHotelActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    }
