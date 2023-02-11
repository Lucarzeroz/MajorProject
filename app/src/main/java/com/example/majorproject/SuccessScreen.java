package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SuccessScreen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static final String UsernameLoggedin = "user that is logged in";
    public static final String URL = "http://192.168.50.200/MP/postComment.php";
    public static final String URL2 = "http://192.168.50.200/MP/Like.php";

    ImageView redirectToGuide, redirectToScanner, redirectToPost, redirectToSettings, redirectToHome, postComment;
    TextInputEditText contenttocomment;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    List<Posts> postsList;

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
            case R.id.Scanner:
                Intent gotoscanner = new Intent(getBaseContext(), MainActivity.class);
                startActivity(gotoscanner);
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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postsList.add(
                new Posts("test", "1700", "testing123", "this is a test", R.drawable.ic_email, R.drawable.ic_send)
        );

        postsList = new ArrayList<>();

        postAdapter = new PostAdapter(this, postsList);
        recyclerView.setAdapter(postAdapter);

        redirectToGuide = findViewById(R.id.redirectToGuideBtn2);
        redirectToScanner = findViewById(R.id.redirectToScannerBtn);
        redirectToPost = findViewById(R.id.redirectToPostImageBtn);
        redirectToSettings = findViewById(R.id.redirectToSettingsBtn);
        redirectToHome = findViewById(R.id.homebtn);
        postComment = findViewById(R.id.sendCommentBtn);
        contenttocomment = findViewById(R.id.ContentForComment);

        Intent togetusername = getIntent();
        String Username = togetusername.getStringExtra(login.Getusername);
        TextView usernametodisplay = (TextView) findViewById(R.id.usernametext2);
        usernametodisplay.setText(Username);

        redirectToGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(), Guides.class);
                startActivity(intent1);
                finish();
            }
        });

        redirectToScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });

        redirectToPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getBaseContext(), PostScreen.class);
                intent3.putExtra(UsernameLoggedin, Username);
                startActivity(intent3);
                finish();
            }
        });

        redirectToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getBaseContext(), UserSettings.class);
                startActivity(intent4);
                finish();
            }
        });

        redirectToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getBaseContext(), SuccessScreen.class);
                startActivity(intent5);
                finish();
            }
        });

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content;
                content = String.valueOf(contenttocomment.getText());
                if(!content.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "content";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = Username;
                            data[1] = content;
                            PutData putData = new PutData(URL, "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Successful")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),"Please ensure all fields are filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}