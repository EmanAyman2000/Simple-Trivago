package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_room);
      // int hotelID = Integer.parseInt(getIntent().getStringExtra("HotelID").toString());
    String s = getIntent().getExtras().getString("HotelID");
        int hotelID = Integer.parseInt(s);
        TableLayout tblShow = (TableLayout) findViewById(R.id.tblShowRoom);
        TrivagoDBHelper helper = new TrivagoDBHelper(this);

        final Cursor cursor = helper.getRooms(hotelID);
        while(!cursor.isAfterLast()) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            TextView txtRoomNumber = new TextView(this);
            txtRoomNumber.setText( "                " +cursor.getString(2).toString());
            row.addView(txtRoomNumber);

            TextView txtRoomType = new TextView(this);
            txtRoomType.setText( "                                                 " +cursor.getString(3).toString());
            row.addView(txtRoomType);

            tblShow.addView(row , new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            cursor.moveToNext();
        }

    }
}