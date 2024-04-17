package com.example.prac;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtid;
    private EditText edtname;
    private EditText edtphn;
    private EditText edtcountry;
    private String id;
    private String name;
    private String phone;
    private String country;
    private ProductAdapter productAdapter;
    private DatabaseHelper databaseHelper;
    private RecyclerView productRecyclerView;
    private Button addbtn;
    private Button sort;
    private Button find;
    private Button update;
    private Spinner spinner;

    String[] countries ={ "India", "USA", "UK", "Russia", "Germany"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtid = findViewById(R.id.editTextText);
        edtname = findViewById(R.id.editTextText2);
        edtphn = findViewById(R.id.editTextText3);
        edtcountry = findViewById(R.id.editTextText4);
        databaseHelper = new DatabaseHelper(this);
        productRecyclerView = findViewById(R.id.recyclerView);
        addbtn = findViewById(R.id.button);
        sort = findViewById(R.id.button2);
        find = findViewById(R.id.button3);
        update = findViewById(R.id.button4);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        spinner.setAdapter(countriesAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                List<Employee> productList = databaseHelper.getSpecificEmployees(selectedItem);
                productAdapter = new ProductAdapter(MainActivity.this, productList);
                productRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                productRecyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = edtid.getText().toString().trim();
                name = edtname.getText().toString().trim();
                phone = edtphn.getText().toString().trim();
                country = edtcountry.getText().toString().trim();
                edtid.setText("");
                edtname.setText("");
                edtphn.setText("");
                edtcountry.setText("");
                Employee emp = new Employee(id,name,phone,country);
                databaseHelper.addEmployee(emp);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = edtid.getText().toString().trim();
                country=edtcountry.getText().toString().trim();
                databaseHelper.updateEmployee(id,country);
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Employee> productList = databaseHelper.getAllSortedEmployees();
                productAdapter = new ProductAdapter(MainActivity.this, productList);
                productRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                productRecyclerView.setAdapter(productAdapter);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = edtid.getText().toString().trim();
                edtid.setText("");
                List<Employee> productList = databaseHelper.getone(id);
                productAdapter = new ProductAdapter(MainActivity.this, productList);
                productRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                productRecyclerView.setAdapter(productAdapter);
            }
        });

    }
}