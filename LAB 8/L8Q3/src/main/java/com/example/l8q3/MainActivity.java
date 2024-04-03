package com.example.l8q3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView doctorListView;
    private ArrayAdapter<String> adapter;
    private List<Doctor> doctorList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doctorListView = findViewById(R.id.doctorListView);
        databaseHelper = new DatabaseHelper(this);

        // Add some sample doctors to the list (you can replace this with actual database data)
        addSampleDoctors();

        // Initialize adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getDoctorNames());
        doctorListView.setAdapter(adapter);

        // Set item click listener
        doctorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doctor selectedDoctor = doctorList.get(position);
                boolean newStatus = !selectedDoctor.isFree(); // Toggle status
                databaseHelper.updateDoctorStatus(selectedDoctor.getId(), newStatus);
                selectedDoctor.setFree(newStatus);
                adapter.notifyDataSetChanged();
                String message = "Doctor " + selectedDoctor.getName() + " is now " + (newStatus ? "available" : "occupied");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addSampleDoctors() {
        databaseHelper.addDoctor(new Doctor(1, "Dr. Smith", true));
        databaseHelper.addDoctor(new Doctor(2, "Dr. Johnson", false));
        databaseHelper.addDoctor(new Doctor(3, "Dr. Patel", true));
        databaseHelper.addDoctor(new Doctor(4, "Dr. Garcia", true));
        databaseHelper.addDoctor(new Doctor(5, "Dr. Lee", false));
        databaseHelper.addDoctor(new Doctor(6, "Dr. Nguyen", true));
        databaseHelper.addDoctor(new Doctor(7, "Dr. Khan", true));
        databaseHelper.addDoctor(new Doctor(8, "Dr. Martinez", false));
        databaseHelper.addDoctor(new Doctor(9, "Dr. Anderson", true));
        databaseHelper.addDoctor(new Doctor(10, "Dr. Gonzalez", true));

        // Add more doctors as needed
    }

    private List<String> getDoctorNames() {
        doctorList = databaseHelper.getAllDoctors();
        List<String> doctorNames = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            doctorNames.add(doctor.getName() + " (" + (doctor.isFree() ? "Available" : "Occupied") + ")");
        }
        return doctorNames;
    }
}
