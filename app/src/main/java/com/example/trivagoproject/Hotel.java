package com.example.trivagoproject;

public class Hotel {

    private String name;
    private String location;
    private int freeMeals;
    private int gym;
    private int pool;
    private int NumOfStars;


    /*public Hotel(String name, String location, int freeMeals, int gym, int pool){
        this.name=name;
        this.location=location;
        this.freeMeals=freeMeals;
        this.gym=gym;
        this.pool=pool;
    }*/

    public Hotel(String name, String location, int freeMeals, int gym, int pool , int NumOfStars){

        this.name=name;
        this.location=location;
        this.freeMeals=freeMeals;
        this.gym=gym;
        this.pool=pool;
        this.NumOfStars = NumOfStars;
    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }




    public int getFreeMeals() {
        return freeMeals;
    }

    public int getGym() {
        return gym;
    }

    public int getPool() {
        return pool;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public void setFreeMeals(int freeMeals) {
        this.freeMeals = freeMeals;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }

    public void setPool(int pool) {
        this.pool = pool;
    }

    public int getNumOfStars() {
        return NumOfStars;
    }

    public void setNumOfStars(int numOfStars) {
        NumOfStars = numOfStars;
    }
}
