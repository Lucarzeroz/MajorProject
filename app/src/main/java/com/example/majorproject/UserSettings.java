package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class UserSettings extends AppCompatActivity {

    public static final String Updatedusersname = "example";

    TextInputEditText updateFullname, updateUsername, updatePassword, updateEmail, usernameToUpdate;
    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        EditText editFullname = (EditText) findViewById(R.id.UpdateFullname);
        editFullname.setText("To Update Fullname", TextView.BufferType.EDITABLE);
        EditText editUsername = (EditText) findViewById(R.id.UpdateUsername);
        editUsername.setText("To Update Username", TextView.BufferType.EDITABLE);
        EditText editPassword = (EditText) findViewById(R.id.UpdatePassword);
        editPassword.setText("To Update Password", TextView.BufferType.EDITABLE);
        EditText editEmail = (EditText) findViewById(R.id.UpdateEmail);
        editEmail.setText("To Update Email", TextView.BufferType.EDITABLE);

        updateFullname = findViewById(R.id.UpdateFullname);
        updateUsername = findViewById(R.id.UpdateUsername);
        updatePassword = findViewById(R.id.UpdatePassword);
        updateEmail = findViewById(R.id.UpdateEmail);
        usernameToUpdate = findViewById(R.id.usernameToUpdate);
        updateBtn = findViewById(R.id.UpdateDetailsBtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedfullname, updatedusername, updatedpassword, updatedemail, UsernameToUpdate;
                updatedfullname = String.valueOf(updateFullname.getText());
                updatedusername = String.valueOf(updateUsername.getText());
                updatedpassword = String.valueOf(updatePassword.getText());
                updatedemail = String.valueOf(updateEmail.getText());
                UsernameToUpdate = String.valueOf(usernameToUpdate.getText());


                if(!updatedfullname.equals("") && !updatedusername.equals("") && !updatedpassword.equals("") && !updatedemail.equals("") && !UsernameToUpdate.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[5];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            field[4] = "id";
                            String[] data = new String[5];
                            data[0] = updatedfullname;
                            data[1] = updatedusername;
                            data[2] = updatedpassword;
                            data[3] = updatedemail;
                            data[4] = UsernameToUpdate;
                            PutData putData = new PutData("http://192.168.50.200/MP/UpdateUser.php", "POST", field, data);
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
    }
}