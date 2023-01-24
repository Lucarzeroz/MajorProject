package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DiffTypesOfScams extends AppCompatActivity {

    ImageView GuidesBtn, PostBtn, ProfileBtn;
    TextView Phishing, Impersonation, Love, ECommerce, Job, Investment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_types_of_scams);

        GuidesBtn = findViewById(R.id.redirectToGuideBtn);
        PostBtn =findViewById(R.id.redirectToPostBtn2);
        ProfileBtn = findViewById(R.id.redirectToUserBtn2);
        Phishing = findViewById(R.id.PhishingScamsText);
        Impersonation = findViewById(R.id.ImpersonationScamsText);
        Love = findViewById(R.id.LoveScamsText);
        ECommerce = findViewById(R.id.ECommerceScamsText);
        Job = findViewById(R.id.JobScamsText);
        Investment = findViewById(R.id.InvestmentScamsText);

        GuidesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(), Guides.class);
                startActivity(intent1);
                finish();
            }
        });

        PostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getBaseContext(), PostScreen.class);
                startActivity(intent2);
                finish();
            }
        });

        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent3);
                finish();
            }
        });

        Phishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent4);
                finish();
            }
        });

        Phishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent4);
                finish();
            }
        });

        Impersonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent5);
                finish();
            }
        });

        Love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent6);
                finish();
            }
        });

        ECommerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent7);
                finish();
            }
        });

        Job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent8 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent8);
                finish();
            }
        });

        Investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent9 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent9);
                finish();
            }
        });
    }
}