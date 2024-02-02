package com.example.background;




import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerColors;
    private RelativeLayout mainLayout;
    private TextView textViewPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerColors = findViewById(R.id.spinnerColors);
        mainLayout = findViewById(R.id.mainLayout);
        textViewPrompt = findViewById(R.id.textViewPrompt);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.color_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerColors.setAdapter(adapter);

        spinnerColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                changeBackgroundColor(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    private void changeBackgroundColor(int position) {
        int color;
        switch (position) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.BLUE;
                break;
            case 3:
                color = Color.YELLOW;
                break;
            case 4:
                color = Color.GRAY;
                break;
            default:
                color = Color.WHITE;
                break;
        }

        mainLayout.setBackgroundColor(color);
        textViewPrompt.setTextColor(getContrastColor(color));
    }

    private int getContrastColor(int backgroundColor) {
        // Calculate the contrast color based on the background color
        double luminance = (0.299 * Color.red(backgroundColor) + 0.587 * Color.green(backgroundColor) + 0.114 * Color.blue(backgroundColor)) / 255;
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;
    }
}
