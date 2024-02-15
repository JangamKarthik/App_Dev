package com.example.grocery;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edt;
    private RecyclerView recyclerView1;
    private Button buttonSubmit;
    private List<GroceryItem> groceryItemList;
    private GroceryAdapter groceryAdapter;
    int total = 0;

    int selected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.recyclerView);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        edt = findViewById(R.id.editTextText);
        groceryItemList = new ArrayList<>();
        groceryItemList.add(new GroceryItem("Item 1",5));
        groceryItemList.add(new GroceryItem("Item 2",3));
        groceryItemList.add(new GroceryItem("Item 3",4));
        groceryItemList.add(new GroceryItem("Item 4",6));
        groceryItemList.add(new GroceryItem("Item 5",7));
       // groceryItemList.add(new GroceryItem("Item 6",));
        //groceryItemList.add(new GroceryItem("Item 7"));
        //groceryItemList.add(new GroceryItem("Item 8"));
        //groceryItemList.add(new GroceryItem("Item 9"));
        //groceryItemList.add(new GroceryItem("Item 10"));

        groceryAdapter = new GroceryAdapter(groceryItemList);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(groceryAdapter);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printSelectedItems();
            }
        });
    }

    private void printSelectedItems() {
       /* StringBuilder selectedItems = new StringBuilder("Selected Items:\n");
        for (GroceryItem item : groceryItemList) {
            if (item.isSelected()) {
                selectedItems.append(item.getName()).append("\n");
            }
        }*/
        String selectedItems = edt.getText().toString().trim();
        int length = selectedItems.length();
        String len =  Integer.valueOf(length).toString();
        Toast.makeText(this,len, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, MainActivity2.class);

        total = calculate();
        selected = sec();

        if(length==selected){
            i.putExtra("MY_KEY",total);
        }
        else{
            i.putExtra("MY_KEY",selected);
        }
        startActivity(i);
    }

    public int calculate(){
        int res = 0;
        for(GroceryItem item : groceryItemList){
            if(item.isSelected()){
                res+=item.getPrice();
            }
        }
        return res;
    }

    public int sec(){
        int res = 0;
        for(GroceryItem item : groceryItemList){
            if(item.isSelected()){
                res++;
            }
        }
        return res;
    }
}