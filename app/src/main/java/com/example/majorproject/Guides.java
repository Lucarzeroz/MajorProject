package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Guides extends AppCompatActivity {

    ImageView redirectToGuide, redirectToPost, redirectToUser;
    TextView HowToIdentifyScamText, DiffTypesOfScamsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        redirectToGuide = findViewById(R.id.redirectToGuideBtn);
        redirectToPost = findViewById(R.id.redirectToPostBtn2);
        redirectToUser = findViewById(R.id.redirectToUserBtn2);
        HowToIdentifyScamText = findViewById(R.id.howToIdentifyScamsText);
        DiffTypesOfScamsText = findViewById(R.id.DifferentTypesOfScamsText);

        redirectToGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(), Guides.class);
                startActivity(intent1);
                finish();
            }
        });

        redirectToPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getBaseContext(), PostScreen.class);
                startActivity(intent2);
                finish();
            }
        });

        redirectToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent3);
                finish();
            }
        });

        HowToIdentifyScamText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getBaseContext(), HowToIdentifyScam.class);
                startActivity(intent4);
                finish();
            }
        });

        DiffTypesOfScamsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getBaseContext(), DiffTypesOfScams.class);
                startActivity(intent5);
                finish();
            }
        });
    }
}