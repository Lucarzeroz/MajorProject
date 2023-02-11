package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import javax.xml.validation.Validator;

public class UserSettings extends AppCompatActivity {

    public static final String Updatedusersname = "example";

    TextInputEditText updateFullname, updateUsername, updatePassword, updateEmail;
    Button updateBtn , logoutbtn;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        updateFullname = findViewById(R.id.UpdateFullname);
        updateUsername = findViewById(R.id.UpdateUsername);
        updatePassword = findViewById(R.id.UpdatePassword);
        updateEmail = findViewById(R.id.UpdateEmail);
        updateBtn = findViewById(R.id.UpdateDetailsBtn);
        logoutbtn = findViewById(R.id.logoutbtn);
        backbtn = findViewById(R.id.backbtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedfullname, updatedusername, updatedpassword, updatedemail;
                updatedfullname = String.valueOf(updateFullname.getText());
                updatedusername = String.valueOf(updateUsername.getText());
                updatedpassword = String.valueOf(updatePassword.getText());
                updatedemail = String.valueOf(updateEmail.getText());

                if(!updatedfullname.equals("") && !updatedusername.equals("") && !updatedpassword.equals("") && !updatedemail.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[5];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            field[4] = "fullname";
                            String[] data = new String[5];
                            data[0] = updatedfullname;
                            data[1] = updatedusername;
                            data[2] = updatedpassword;
                            data[3] = updatedemail;
                            data[4] = updatedfullname;
                            PutData putData = new PutData("http://172.30.80.253/MP/UpdateUser.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Update Successful")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        EditText updatedusername = (EditText) findViewById(R.id.UpdateUsername);
                                        String UpdatedUsername = updatedusername.getText().toString();
                                        Intent intent = new Intent(getBaseContext(), SuccessScreen.class);
                                        intent.putExtra(Updatedusersname, UpdatedUsername);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"Please ensure all fields are filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Logged Out Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getBaseContext(), SuccessScreen.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}