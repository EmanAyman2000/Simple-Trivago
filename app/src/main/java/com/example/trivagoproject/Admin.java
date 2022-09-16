package com.example.trivagoproject;

public class Admin extends  User
{
    final private int isAdmin;

    public Admin(String name , String email ,String password){
        super(name, email, password);
        isAdmin=1;
    }

    public int getIsAdmin() {
        return isAdmin;
    }
}
