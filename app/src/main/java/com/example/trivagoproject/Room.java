package com.example.trivagoproject;

public class Room {
    private int roomID ;
    private int roomNumber ;
    private String roomType;
    private int hotelID ;

    public Room(int roomID ,int roomNumber ,String roomType , int hotelID)
    {
        this.roomID = roomID;
        this.roomNumber = roomNumber ;
        this.roomType = roomType ;
        this.hotelID = hotelID ;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }
}
