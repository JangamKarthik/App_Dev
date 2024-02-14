package com.example.bgco;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainLayout;
    private Spinner backgroundSpinner;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayoutx);
        backgroundSpinner = findViewById(R.id.backgroundSpinner);
        applyButton = findViewById(R.id.applyButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.background_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(adapter);

        backgroundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                changeBackgroundColor(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedPosition = backgroundSpinner.getSelectedItemPosition();
                changeBackgroundColor(selectedPosition);
            }
        });
    }

    private void changeBackgroundColor(int position) {
        int colorId;
        switch (position) {
            case 0:
                colorId = android.R.color.holo_blue_light;
                break;
            case 1:
                colorId = android.R.color.holo_green_light;
                break;
            case 2:
                colorId = android.R.color.holo_red_light;
                break;
            default:
                colorId = android.R.color.white;
        }

        int color = getResources().getColor(colorId);
        mainLayout.setBackgroundColor(color);
    }
}
