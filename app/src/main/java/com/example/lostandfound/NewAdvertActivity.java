package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NewAdvertActivity extends AppCompatActivity {

    EditText newNameEditText;
    EditText newPhoneEditText;
    EditText newDescriptionEditText;
    EditText newDateEditText;
    EditText newLocationEditText;
    Button newSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);

        newNameEditText = findViewById(R.id.newNameEditText);
        newPhoneEditText = findViewById(R.id.newPhoneEditText);
        newDescriptionEditText = findViewById(R.id.newDescriptionEditText);
        newDateEditText = findViewById(R.id.newDateEditText);
        newLocationEditText = findViewById(R.id.newLocationEditText);
        newSaveButton = findViewById(R.id.newSaveButton);

        newSaveButton.setOnClickListener(view -> {
            String name = newNameEditText.getText().toString();
            String phone = newPhoneEditText.getText().toString();
            String description = newDescriptionEditText.getText().toString();
            String date = newDateEditText.getText().toString();
            String location = newLocationEditText.getText().toString();
        });
    }
}