package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SuccessScreen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static final String Username = "Username";
    public static final String updatedUsername = "Updated Username";


    Button btnlogout, AddPostBtn, SettingsBtn;
    ImageView redirectToGuide;

    //to make to popup menu appear when the hamburger menu is pressed
    public void showpopup(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    //cases for when each item is clicked
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Guides:
                Intent gotoguides = new Intent(getBaseContext(), Guides.class);
                startActivity(gotoguides);
                finish();
                return true;
            case R.id.Posts:
                Intent gotoposts = new Intent(getBaseContext(), PostScreen.class);
                startActivity(gotoposts);
                finish();
                return true;
            case R.id.Settings:
                Intent gotosettings = new Intent(getBaseContext(), UserSettings.class);
                startActivity(gotosettings);
                finish();
                return true;
            case R.id.LogOut:
                Intent gotologout = new Intent(getBaseContext(), MainScreen.class);
                startActivity(gotologout);
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);


        btnlogout = findViewById(R.id.logoutbtn);
        AddPostBtn = findViewById(R.id.redirectToPostbtn);
        SettingsBtn = findViewById(R.id.redirectToUserbtn);
        redirectToGuide = findViewById(R.id.redirectToGuideBtn2);

        Intent intent = getIntent();
        String username = intent.getStringExtra(signup.Getusername);
        TextView welcomeuser = (TextView) findViewById(R.id.welcomeUsername);
        welcomeuser.setText(username);

        Intent intent5 = getIntent();
        String updatedusername = intent5.getStringExtra(UserSettings.Updatedusersname);
        TextView usernname = (TextView) findViewById(R.id.UpdatedUsername);
        usernname.setText(updatedusername);

        TextView usernameDisplayed = (TextView) findViewById(R.id.welcomeUsername);
        String usernameOnDisplay = usernameDisplayed.getText().toString();
        intent.putExtra(Username, usernameOnDisplay);

        TextView updatedUsernameDisplayed = (TextView) findViewById(R.id.UpdatedUsername);
        String updatedUsernameOnDisplay = updatedUsernameDisplayed.getText().toString();
        intent.putExtra(updatedUsername, updatedUsernameOnDisplay);

        redirectToGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(getBaseContext(), Guides.class);
                startActivity(intent6);
                finish();
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getBaseContext(), MainScreen.class);
                startActivity(intent2);
                finish();
            }
        });

        AddPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3  = new Intent(getBaseContext(), PostScreen.class);
                startActivity(intent3);
                finish();
            }
        });

        SettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent4);
                finish();
            }
        });

    }
}