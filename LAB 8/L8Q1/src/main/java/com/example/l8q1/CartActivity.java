package com.example.l8q1;

// CartActivity.java
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private TextView totalPriceTextView;
    private List<Product> selectedProducts;
    private ArrayAdapter<Product> adapter;
    private double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        // Get selected products from intent
        selectedProducts = (ArrayList<Product>) getIntent().getSerializableExtra("selectedProducts");

        // Calculate total price and update UI
        calculateTotalPrice();
        updateUI();
    }

    private void calculateTotalPrice() {
        for (Product product : selectedProducts) {
            totalPrice += product.getPrice();
        }
    }

    private void updateUI() {
        // Initialize adapter with selected products
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedProducts);
        cartListView.setAdapter(adapter);

        // Display total price
        totalPriceTextView.setText(String.format("Total Price: $%.2f", totalPrice));
    }
}
