package com.example.ncrypt;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonEncrypt;
    private EditText editTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        buttonEncrypt = findViewById(R.id.buttonEncrypt);
        editTextResult = findViewById(R.id.editTextResult);

        buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptText();
            }
        });
    }

    private void encryptText() {
        String inputText = editTextInput.getText().toString();
        String encryptedText = encryptCaesarCipher(inputText);
        editTextResult.setText(encryptedText);
    }

    private String encryptCaesarCipher(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                char encryptedChar = (char) ((currentChar + 3 - 'A') % 26 + 'A');
                result.append(encryptedChar);
            }
            else if (Character.isLowerCase(currentChar)) {
                char encryptedChar = (char) ((currentChar + 3 - 'a') % 26 + 'a');
                result.append(encryptedChar);
            }
            else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}
