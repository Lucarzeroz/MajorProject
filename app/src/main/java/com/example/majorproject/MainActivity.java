//package com.example.scammagram40;
//
//import android.Manifest;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import com.example.scammagram40.R;
//import com.example.scammagram40.SmsReceiver;
//
//public class MainActivity extends AppCompatActivity {
//
//    private SmsReceiver mSmsReceiver;
//    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Register the SMS receiver
//        mSmsReceiver = new SmsReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        registerReceiver(mSmsReceiver, intentFilter);
//
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Request permission from the user
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.READ_SMS},
//                    MY_PERMISSIONS_REQUEST_READ_SMS);
//        }
//
//
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        // Handle the permission request result
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_PERMISSIONS_REQUEST_READ_SMS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission was granted
//                // Do something with the SMS messages
//                Toast.makeText(this, "Access granted!", Toast.LENGTH_SHORT).show();
//                // ...
//            } else {
//                // Permission was denied
//                // Show a message to the user explaining why the app needs this permission
//                Toast.makeText(this, "Access not granted.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Unregister the SMS receiver
//        unregisterReceiver(mSmsReceiver);
//    }
//}
//
package com.example.majorproject;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;
import android.Manifest;
import com.example.majorproject.CallReceiver;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;




public class MainActivity extends AppCompatActivity {

    private SmsReceiver mSmsReceiver;
    private CallReceiver mCallReceiver;
    //    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;
    private static final int REQUEST_READ_AND_RECEIVE_SMS = 1;

    private static final int REQUEST_READ_CONTACTS = 2;
    private static final int REQUEST_READ_PHONE_STATE = 3;
    private boolean isSmsReceiverRegistered = false;
    private boolean isCallReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageScanner.class);
                startActivity(intent);
            }
        });

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
            // At least one permission is not granted, so request them
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                    REQUEST_READ_AND_RECEIVE_SMS);
        }

        Switch mySwitch2 = findViewById(R.id.switch1);
        mySwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The switch is turned on
                    Toast.makeText(MainActivity.this, "Call Listening turned ON", Toast.LENGTH_SHORT).show();
                    if(!isCallReceiverRegistered) {
                        mCallReceiver = new CallReceiver();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("android.intent.action.PHONE_STATE");
                        registerReceiver(mCallReceiver, intentFilter);
                        isCallReceiverRegistered = true;
                    }
                    //check contact permissions in case of exceptions
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                    } else {


                        //Permission to read contacts is already granted, so proceed with the action
//                            Toast.makeText(MainActivity.this, "Contacts acccess granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Call listening turned OFF", Toast.LENGTH_SHORT).show();
                    // The switch is turned off
                    if(isCallReceiverRegistered) {
                        unregisterReceiver(mCallReceiver);
                        isCallReceiverRegistered = false;
                    }
                }
            }
        });





        Switch mySwitch = findViewById(R.id.switch3);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The switch is turned on
                    Toast.makeText(MainActivity.this, "Sms Listening turned ON", Toast.LENGTH_SHORT).show();
                    if(!isSmsReceiverRegistered) {
                        mSmsReceiver = new SmsReceiver();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
                        registerReceiver(mSmsReceiver, intentFilter);
                        isSmsReceiverRegistered = true;
                    }
                    //check contact permissions in case of exceptions
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
                    } else {


                        //Permission to read contacts is already granted, so proceed with the action
//                            Toast.makeText(MainActivity.this, "Contacts acccess granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Sms listening turned OFF", Toast.LENGTH_SHORT).show();
                    // The switch is turned off
                    if(isSmsReceiverRegistered) {
                        unregisterReceiver(mSmsReceiver);
                        isSmsReceiverRegistered = false;
                    }
                }
            }
        });

    }






    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_AND_RECEIVE_SMS) {
            // If the request is cancelled, the result arrays are empty
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Both permissions were granted, so proceed with the action
                // (for example, register the SMS receiver)
                Toast.makeText(this, "Read and receive SMS permissions were granted", Toast.LENGTH_SHORT).show();





                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
                } else {


                    //Permission to read contacts is already granted, so proceed with the action
//                            Toast.makeText(MainActivity.this, "Contacts acccess granted", Toast.LENGTH_SHORT).show();
                }





            } else {
                // At least one permission was not granted, so show a message
                // to the user explaining why the action is not possible
                Toast.makeText(this, "Read and receive SMS permissions are required to do this action", Toast.LENGTH_SHORT).show();
            }









        }
    }

}