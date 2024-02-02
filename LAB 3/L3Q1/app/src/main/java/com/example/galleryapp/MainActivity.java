package com.example.galleryapp;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), "Image " + (position + 1), Toast.LENGTH_SHORT).show();

                int selectedImageId = ((ImageAdapter) parent.getAdapter()).getItem(position);

                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(selectedImageId);

                Toast toast = new Toast(getApplicationContext());
                toast.setView(imageView);
                toast.show();
            }
        });

    }
}
