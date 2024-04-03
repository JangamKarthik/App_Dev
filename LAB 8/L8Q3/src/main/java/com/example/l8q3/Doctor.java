package com.example.l8q3;

public class Doctor {
    private int id;
    private String name;
    private boolean isFree;

    public Doctor(int id, String name, boolean isFree) {
        this.id = id;
        this.name = name;
        this.isFree = isFree;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
