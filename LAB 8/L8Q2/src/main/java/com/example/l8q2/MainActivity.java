package com.example.l8q2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView productRecyclerView;
    private TextView maxPriceTextView, minPriceTextView;
    private ProductAdapter productAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productRecyclerView = findViewById(R.id.productRecyclerView);
        maxPriceTextView = findViewById(R.id.maxPriceTextView);
        minPriceTextView = findViewById(R.id.minPriceTextView);

        databaseHelper = new DatabaseHelper(this);

        // Inserting some sample products into the database
        addSampleProductsToDB();

        // Fetch all products from the database
        List<Product> productList = databaseHelper.getAllProducts();

        // Set up RecyclerView
        productAdapter = new ProductAdapter(this, productList);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setAdapter(productAdapter);

        // Display max and min priced items
        displayMaxAndMinPricedItems(productList);
    }

    private void addSampleProductsToDB() {
        databaseHelper.addProduct(new Product(1, "Product 1", 10.99, "Category A"));
        databaseHelper.addProduct(new Product(2, "Product 2", 20.99, "Category B"));
        databaseHelper.addProduct(new Product(3, "Product 3", 30.99, "Category C"));
    }

    private void displayMaxAndMinPricedItems(List<Product> productList) {
        if (productList != null && !productList.isEmpty()) {
            // Find max and min priced items
            Product maxPriceProduct = Collections.max(productList, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
            Product minPriceProduct = Collections.min(productList, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));

            // Display max and min priced items
            maxPriceTextView.setText("Max Price: " + maxPriceProduct.getName() + " ($" + maxPriceProduct.getPrice() + ")");
            minPriceTextView.setText("Min Price: " + minPriceProduct.getName() + " ($" + minPriceProduct.getPrice() + ")");
        }
    }
}
