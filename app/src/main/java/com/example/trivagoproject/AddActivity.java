package com.example.trivagoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    TrivagoDBHelper db;

    Button add_button, retrieve_button;
    EditText name_editText, location_editText , stars_editText;

    CheckBox meals_checkbox, pool_checkbox, gym_checkbox;

    int hasMeals=0, hasGYM=0, hasPool=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = new TrivagoDBHelper(this);

        add_button = findViewById(R.id.add_btn);
     //   retrieve_button = findViewById(R.id.retrieve_btn);

     //   id_editText= findViewById(R.id.id_textbox);
        name_editText = findViewById(R.id.hotelName_textbox);
        location_editText = findViewById(R.id.hotelLocation_textbox);
        stars_editText = findViewById(R.id.stars_textbox);

        meals_checkbox = findViewById(R.id.meals_chechBox);
        pool_checkbox = findViewById(R.id.pool_chechBox);
        gym_checkbox = findViewById(R.id.gym_chechBox);

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            //    int id = Integer.parseInt(id_editText.getText().toString());
                if(!TextUtils.isEmpty(name_editText.getText().toString()) && !TextUtils.isEmpty(location_editText.getText().toString()) && !TextUtils.isEmpty(stars_editText.getText().toString())) {
                    String name = name_editText.getText().toString();
                    String location = location_editText.getText().toString();
                    int stars = Integer.parseInt(stars_editText.getText().toString());

                    int hasMeals = 0, hasGYM = 0, hasPool = 0;

                    if (meals_checkbox.isChecked())
                        hasMeals = 1;
                    if (gym_checkbox.isChecked())
                        hasGYM = 1;
                    if (pool_checkbox.isChecked())
                        hasPool = 1;

                    Hotel hotel = new Hotel( name, location, hasMeals, hasGYM, hasPool, stars);

                    Cursor c = db.getHotelsIDs(name);
                    if(c.getCount() ==  0)
                    {
                        if (db.insertNewHotel(hotel))
                            Toast.makeText(AddActivity.this, "Successful Addition", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddActivity.this, "Error", Toast.LENGTH_SHORT).show();

                        name_editText.setText("");
                        location_editText.setText("");
                        stars_editText.setText("");
                    }
                    else
                        Toast.makeText(AddActivity.this, "Already ُ ُExists", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(AddActivity.this, "You Must Enter All Data", Toast.LENGTH_SHORT).show();
            }
        });




    }
}

