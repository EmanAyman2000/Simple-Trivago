package com.example.trivagoproject;

public class NormalUser extends User
{
    final  private int isAdmin;
    public NormalUser(String name , String email ,String password){
        super(name, email, password);
        isAdmin=0;
    }
    public int getIsAdmin() {
        return isAdmin;
    }
}
