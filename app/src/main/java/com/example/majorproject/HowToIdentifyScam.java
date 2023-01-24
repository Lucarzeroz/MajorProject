package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HowToIdentifyScam extends AppCompatActivity {

    ImageView backToGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_identify_scam);

        backToGuide = findViewById(R.id.backtoGuidesBtn);

        backToGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Guides.class);
                startActivity(intent);
                finish();
            }
        });
    }
}