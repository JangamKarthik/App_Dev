package com.example.prac;

public class Employee {
    String id;
    String name;
    String phn;
    String place;

    public Employee(){

    }

    public Employee(String id, String name, String phn, String place){
        this.id = id;
        this.name = name;
        this.phn = phn;
        this.place = place;
    }

    public String getid(){
        return id;
    }

    public String getname(){
        return name;
    }

    public String getphn(){
        return phn;
    }

    public String getplace(){
        return place;
    }

}
