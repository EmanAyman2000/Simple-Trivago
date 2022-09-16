package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class ShowAllHotels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_hotels);

        TableLayout table = (TableLayout) findViewById(R.id.tableShow);
        TrivagoDBHelper helper = new TrivagoDBHelper(this);


         String s = getIntent().getExtras().getString("name");
        final Cursor cursor ;
         if(s.equals("0"))
             cursor = helper.getalldata();
         else
             cursor = helper.getSpecificHotel(s);


        while(!cursor.isAfterLast())
        {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams( TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            TextView txtName = new TextView(this);

           final int id = Integer.parseInt(cursor.getString(0).toString());

            txtName.setText( "   " + cursor.getString(1).toString());
            row.addView(txtName);

            TextView txtLocation = new TextView(this);
            txtLocation.setText( "      " +cursor.getString(2).toString());
            row.addView(txtLocation);

            TextView txtStars = new TextView(this);
            txtStars.setText("          "+cursor.getString(3));
            row.addView(txtStars);

            TextView txtMeals = new TextView(this);
            if(cursor.getString(4).equals("1"))
                txtMeals.setText("           YES");
            else
                txtMeals.setText("           No");
            row.addView(txtMeals);

            TextView txtGym = new TextView(this);
            if(cursor.getString(5).equals("1"))
                txtGym.setText("         YES");
            else
                txtGym.setText("           No");
            row.addView(txtGym);

            TextView txtPool = new TextView(this);
            if(cursor.getString(6).equals("1"))
                txtPool.setText("        YES");
            else
                txtPool.setText("         No");
            row.addView(txtPool);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ShowAllHotels.this  , ShowRoom.class);
                    String hotID = String.valueOf(id);
                    i.putExtra("HotelID" ,hotID);
                   startActivity(i);
                }
            });
            table.addView(row , new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            cursor.moveToNext();
        }


    }
}