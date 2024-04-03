package com.example.l8q1;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView productRecyclerView;
    private Button checkoutButton;
    private List<Product> productList;
    private ProdctAdapter productAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productRecyclerView = findViewById(R.id.productRecyclerView);
        checkoutButton = findViewById(R.id.checkoutButton);
        databaseHelper = new DatabaseHelper(this);
        productList = generateSampleProducts(); // Generate sample products
        productAdapter = new ProdctAdapter(this, productList);
        productRecyclerView.setAdapter(productAdapter);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder selectedProductsBuilder = new StringBuilder("Selected Products:\n");
                double totalPrice = 0;
                for (Product product : productList) {
                    if (product.isSelected()) {
                        // Append the name of selected product to the StringBuilder
                        selectedProductsBuilder.append(product.getName()).append("\n");
                        totalPrice += product.getPrice();
                        // Save the selected product to the database
                        databaseHelper.addBill(product.getName(), product.getPrice());
                    }
                }
                // Show a toast message with selected products
                Toast.makeText(MainActivity.this, selectedProductsBuilder.toString(), Toast.LENGTH_LONG).show();
                // Show total price
                Toast.makeText(MainActivity.this, "Total Price: $" + totalPrice, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Product> generateSampleProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product 1", 10.99));
        products.add(new Product(2, "Product 2", 20.99));
        products.add(new Product(3, "Product 3", 30.99));
        return products;
    }
}
