package com.example.grocery;


public class GroceryItem {

    private String name;
    private boolean isSelected;

    public GroceryItem(String name) {
        this.name = name;
        this.isSelected = false;
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
}
