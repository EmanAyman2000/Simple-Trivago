package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;

public class HotelFilteration extends AppCompatActivity {
    TrivagoDBHelper trivagoDatabase;
    Button btnFilter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_filteration);

        Button btnFilter = (Button) findViewById(R.id.btnFilter);
        final Spinner sHotelLocation = (Spinner) findViewById(R.id.sHotelLocation);
        final RatingBar rbHotelRate = (RatingBar) findViewById(R.id.rbHotelRate);
        ListView lvShowHotels = (ListView) findViewById(R.id.lvShowFilteredHotels);
         final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext() , R.layout.support_simple_spinner_dropdown_item);
        lvShowHotels.setAdapter(adapter);


        btnFilter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                adapter.clear();
                trivagoDatabase = new TrivagoDBHelper(getApplicationContext());
                float currentrate = rbHotelRate.getRating();
                int rate = Math.round(currentrate) ;
                String selectedLocation = sHotelLocation.getSelectedItem().toString();
                Cursor cursor = trivagoDatabase.GetFilteredHotels(rate , selectedLocation);


                while(!cursor.isAfterLast())
                {
                   adapter.add(cursor.getString(0));
                   cursor.moveToNext();
                }
            }
        });

        lvShowHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(HotelFilteration.this , ShowAllHotels.class);
                String hName = (String) parent.getItemAtPosition(position);
                i.putExtra("name" , hName);
                startActivity(i);
            }
        });
        Button btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HotelFilteration.this  , LogIn.class);
                startActivity(i);
            }
        });

    }
}