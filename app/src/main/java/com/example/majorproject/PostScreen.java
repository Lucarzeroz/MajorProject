package com.example.majorproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PostScreen extends AppCompatActivity {

    TextView usernameText;
    TextInputEditText PostTitle, PostContents;
    Button PostBtn;
    ImageView uploadImgBtn;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_screen);

        usernameText = findViewById(R.id.usernametext);
        PostTitle = findViewById(R.id.postTitle);
        PostContents = findViewById(R.id.postContent);
        PostBtn = findViewById(R.id.PostBtn);
        uploadImgBtn = findViewById(R.id.uploadImgBtn);


        Intent intent = getIntent();
        String loggedinusername = intent.getStringExtra(login.Getusername);
        TextView welcomeuser = (TextView) findViewById(R.id.usernametext);
        welcomeuser.setText(loggedinusername);

        Intent intent3 = getIntent();
        String updatedLoggedInUsername = intent3.getStringExtra(SuccessScreen.updatedUsername);
        TextView updatedUsername = (TextView) findViewById(R.id.UpdatedUsernameText);
        updatedUsername.setText(updatedLoggedInUsername);

        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                uploadImgBtn.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        uploadImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(Intent.ACTION_PICK);
                intent4.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent4);
            }
        });

        PostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username, title, contents, numoflikes;
                username = String.valueOf(loggedinusername);
                title = String.valueOf(PostTitle.getText());
                contents = String.valueOf(PostContents.getText());
                Integer likes = Integer.valueOf("0");
                numoflikes = String.valueOf(likes);

                ByteArrayOutputStream byteArrayOutputStream;
                byteArrayOutputStream = new ByteArrayOutputStream();
                if(bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);

                    if(!title.equals("") && !contents.equals("")) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] field = new String[5];
                                field[0] = "username";
                                field[1] = "title";
                                field[2] = "content";
                                field[3] = "img_path";
                                field[4] = "likes";
                                String[] data = new String[5];
                                data[0] = username;
                                data[1] = title;
                                data[2] = contents;
                                data[3] = base64Image;
                                data[4] = numoflikes;
                                PutData putData = new PutData("http://192.168.50.200/MP/Postpost.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if(result.equals("Post Successful")) {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            Intent intent2 = new Intent(getBaseContext(), SuccessScreen.class);
                                            startActivity(intent2);
                                            finish();
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
                } else {
                    Toast.makeText(getApplicationContext(),"Please ensure an image is selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}