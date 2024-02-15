package com.example.grocery;

public class GroceryItem {
    private String name;
    private boolean isSelected;

    private int price;

    public GroceryItem(String name, int price) {
        this.name = name;
        this.isSelected = false;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getPrice(){
        return price;
    }
}
