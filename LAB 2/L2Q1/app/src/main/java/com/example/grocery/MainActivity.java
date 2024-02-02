package com.example.grocery;




import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private Button buttonSubmit;
    private List<GroceryItem> groceryItemList;
    private GroceryAdapter groceryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.recyclerView);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Initialize the list of grocery items
        groceryItemList = new ArrayList<>();
        groceryItemList.add(new GroceryItem("Item 1"));
        groceryItemList.add(new GroceryItem("Item 2"));
        groceryItemList.add(new GroceryItem("Item 3"));
        groceryItemList.add(new GroceryItem("Item 4"));
        groceryItemList.add(new GroceryItem("Item 5"));
        groceryItemList.add(new GroceryItem("Item 6"));
        groceryItemList.add(new GroceryItem("Item 7"));
        groceryItemList.add(new GroceryItem("Item 8"));
        groceryItemList.add(new GroceryItem("Item 9"));
        groceryItemList.add(new GroceryItem("Item 10"));

        // Set up the RecyclerView
        groceryAdapter = new GroceryAdapter(groceryItemList);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(groceryAdapter);

        // Set up the click listener for the Submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printSelectedItems();
            }
        });
    }

    private void printSelectedItems() {
        StringBuilder selectedItems = new StringBuilder("Selected Items:\n");
        for (GroceryItem item : groceryItemList) {
            if (item.isSelected()) {
                selectedItems.append(item.getName()).append("\n");
            }
        }
        // Display the selected items in a Toast
        Toast.makeText(this, selectedItems.toString(), Toast.LENGTH_LONG).show();
    }
}

