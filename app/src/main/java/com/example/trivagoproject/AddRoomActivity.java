package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddRoomActivity extends AppCompatActivity {
    TrivagoDBHelper db;
    Spinner spinner;
    RadioButton radio1,radio2;
    TextView Text1,Text2;
    Button btn;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        db=new TrivagoDBHelper(this);
        spinner = (Spinner) findViewById(R.id.s);
        radio1 = (RadioButton) findViewById(R.id.r1);
        radio2 = (RadioButton) findViewById(R.id.r2);
        Text1 = (TextView) findViewById(R.id.t1);
        Text2 = (TextView) findViewById(R.id.text2);
        btn = (Button) findViewById(R.id.button);
        arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        Cursor cursor = db.getHotelsNames();
        radio1.setChecked(true);
        while(!cursor.isAfterLast())
        {
            arrayAdapter.add(cursor.getString(0));
            cursor.moveToNext();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(Text1.getText().toString()) && !TextUtils.isEmpty(Text2.getText().toString())) {
                    int RoomId = Integer.parseInt(Text1.getText().toString());
                    int RoomNumber = Integer.parseInt(Text2.getText().toString());
                    String HotelName = spinner.getSelectedItem().toString();
                    String RoomType;
                    if (radio1.isChecked() == true)
                        RoomType = "Double";

                    else
                        RoomType = "Single";
                    Cursor cursor = db.getHotelsIDs(HotelName);
                    int HotelID = 0;
                    while (!cursor.isAfterLast()) {
                        HotelID = Integer.parseInt(cursor.getString(0));
                        cursor.moveToNext();
                    }
                    Room room = new Room(RoomId , RoomNumber,RoomType,HotelID);
                    if( db.addData(room))
                        Toast.makeText(AddRoomActivity.this, "Room Added", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AddRoomActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    Text1.setText("");
                    Text2.setText("");
                }
                else
                    Toast.makeText(AddRoomActivity.this, "You Must Enter All Data", Toast.LENGTH_SHORT).show();
            }

        });
    }
}