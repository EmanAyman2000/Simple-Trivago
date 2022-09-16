package com.example.trivagoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TrivagoDBHelper extends SQLiteOpenHelper
{
    private static String databaseName = "TrivagoDatabase";
    SQLiteDatabase trivagoDatabase;
  //  public static final String database_name="Trivago";
    public static final int database_version=5;

    //HOTEL TABLE
    //columns' names
    public static final String hotel_table = "Hotel";
    public static final String id_column= "HotelID";
    public static final String name_column= "Name";
    public static final String location_column= "location";
  //  public static final String rooms_column= "NoOfRooms";
    public static final String meals_column= "FreeMeals";
    public static final String gym_column= "GYM";
    public static final String pool_column= "Pool";
    public static final String stars_column= "NumOfStars";

    public TrivagoDBHelper(Context context)
    {
       super(context , databaseName , null , database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE Hotel ("+id_column+" INTEGER PRIMARY KEY , "+name_column+"  TEXT, " +
                ""+location_column+"  TEXT, "+stars_column+"  INTEGER," +"" +
                ""+meals_column+"  INTEGER, "+gym_column+"  INTEGER,  "+pool_column+" INTEGER )");
       db.execSQL("create table Room (RoomID integer primary key , HotelID integer not null ,RoomNumber int not null ,RoomType text not null )");
       db.execSQL("create table User ( ID integer primary key , UserName text  ,Password text not null , Email text not null , isAdmin int )");
       //db.execSQL("insert into User(UserName,Email,Password,IsAdmin) values('Admin','a@gmail.com','admin',1)");
     //   db.execSQL("insert into User(UserName,Email,Password,IsAdmin) values('Ahmed','b@gmail.com','1236',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + hotel_table);
        db.execSQL("drop table if exists Room");
        db.execSQL("drop table if exists User");
        onCreate(db);
    }

    // user functions
    public Cursor GetFilteredHotels(int nOfStars , String location)
    {
        trivagoDatabase = getReadableDatabase();

        Cursor cursor = trivagoDatabase.rawQuery("select Name from Hotel where location = '"+ location + "' and NumOfStars = " + nOfStars,null );
        cursor.moveToFirst();
        trivagoDatabase.close();
        return  cursor ;
    }


    public void  AddNewUser(User x )
    {
        trivagoDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username",x.getName());
        contentValues.put("Password",x.getPassword());
        contentValues.put("Email",x.getEmail());
        contentValues.put("isAdmin",x.getIsAdmin());


        trivagoDatabase.insert("User",null,contentValues);
        trivagoDatabase.close();

    }

    public Boolean UserName_Existed(String name){
        trivagoDatabase=getReadableDatabase();
        String[] user = {name};
        Cursor cursor = trivagoDatabase.rawQuery("Select * from User where UserName=?",user);
        cursor.moveToFirst();
        trivagoDatabase.close();
        if(cursor.getCount()>0){ // get number of rows
            return false;
        }
        else{
            return true;
        }
    }
    public boolean validation(String name,String password)
    {
        trivagoDatabase=getReadableDatabase();
        Cursor cursor=trivagoDatabase.rawQuery("select * from User where UserName=? and Password=?",new String[]{name,password});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            trivagoDatabase.close();
            return true;
        }
        cursor.moveToFirst();
        trivagoDatabase.close();
        return false;
    }
    public int checkIsAdmin(String name, String password)
    {
        trivagoDatabase=getReadableDatabase();
        Cursor cursor=trivagoDatabase.rawQuery("select IsAdmin from User where UserName=? and Password=?",new String[]{name,password});
        cursor.moveToFirst();
        trivagoDatabase.close();
        return Integer.valueOf(cursor.getString(0));
    }
    public boolean checkBYName(String name)
    {
        trivagoDatabase=getReadableDatabase();
        Cursor cursor=trivagoDatabase.rawQuery("select * from User where Username=?",new String[]{name});
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
    public void update(String name,String password){
        trivagoDatabase=getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("UserName",name);
        content.put("Password",password);
        trivagoDatabase.update("User",content,"UserName like?",new String[]{name});
        trivagoDatabase.close();
    }


    public boolean insertNewHotel(Hotel hotel){

        SQLiteDatabase trivago_database = getWritableDatabase();
        ContentValues values = new ContentValues();

      //  values.put(id_column , hotel.getId());
        values.put(name_column, hotel.getName());
        values.put(location_column, hotel.getLocation());
        values.put(meals_column, hotel.getFreeMeals());
        values.put(gym_column, hotel.getGym());
        values.put(pool_column, hotel.getPool());
        values.put(stars_column , hotel.getNumOfStars());
    //  values.put(id_column , hotel.getId());

        long newHotel = trivago_database.insert(hotel_table, null, values);
        if(newHotel==-1)
            return false;
        return true;
    }
    public boolean addData(Room room) {
        trivagoDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
     //   contentValues.put("RoomID", room.getRoomID());
        contentValues.put("HotelID", room.getHotelID());
        contentValues.put("RoomNumber", room.getRoomNumber());
        contentValues.put("RoomType", room.getRoomType());
        long result = trivagoDatabase.insert("Room", null, contentValues);
        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getHotelsIDs(String name){
        trivagoDatabase = getReadableDatabase();
        String [] arg = {name};
        Cursor cursor = trivagoDatabase.rawQuery("select HotelID from Hotel where Name = ? ", arg );
        cursor.moveToFirst();
        return  cursor ;
    }
    public  Cursor getHotelsNames()
    {
        trivagoDatabase = getReadableDatabase();
        Cursor data = trivagoDatabase.rawQuery("select Name from Hotel" , null);
        data.moveToFirst();
        return data;
    }

    public boolean DeleteHotel(int id)
    {
        long res = trivagoDatabase.delete("Hotel", "HotelID" + " = " + id, null) ;
        if (res == -1)
            return false;
         else
            return true;
    }

    public Cursor getalldata()
    {
        SQLiteDatabase db =this .getWritableDatabase();
        Cursor res=db.rawQuery("select * from Hotel",null);
         res.moveToFirst();
        return res;
    }

    public Cursor getRooms(int hotelID)
    {
        SQLiteDatabase db =this .getWritableDatabase();
        Cursor res=db.rawQuery("select * from Room where HotelID = " + hotelID,null);
        res.moveToFirst();
        return res;
    }

    public Cursor getSpecificHotel(String name)
    {
        trivagoDatabase = getReadableDatabase();

        Cursor cursor = trivagoDatabase.rawQuery("select * from Hotel where Name = '" + name + "'" ,null );
        cursor.moveToFirst();
        trivagoDatabase.close();
        return  cursor ;
    }

    }



