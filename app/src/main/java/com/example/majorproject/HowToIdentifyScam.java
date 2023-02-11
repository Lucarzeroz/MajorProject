package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.Delayed;

public class HowToIdentifyScam extends AppCompatActivity {

    ImageView backToGuide;
    Button Yesbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_identify_scam);

        backToGuide = findViewById(R.id.backtoGuidesBtn);
        Yesbtn = findViewById(R.id.YesButton);

        backToGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Guides.class);
                startActivity(intent);
                finish();
            }
        });

        Yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HowToIdentifyScam.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), Guides.class);
                startActivity(intent);
                finish();
            }
        });
    }
}