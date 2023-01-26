package com.example.majorproject;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//import org.python.core.PyObject;
//import org.python.util.PythonInterpreter;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MessageScanner extends Activity {

    private static final int REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_scan);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {
            // Permission is granted, you can proceed with your action
            // Your code to run the script and read the csv file

        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, you can proceed with your action
                // Your code to run the script and read the csv file
                // ...
                Toast.makeText(this, "External storage permission granted", Toast.LENGTH_SHORT).show();


                AssetManager assetManager = getAssets();
                try {
                    // Read the Python script from the assets folder
                    InputStream scriptStream = assetManager.open("main.py");
                    File script = new File(getFilesDir(), "main.py");
                    FileOutputStream fos = new FileOutputStream(script);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = scriptStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.flush();
                    fos.close();
                    scriptStream.close();

                    // Read the csv file from the assets folder
                    InputStream csvStream = assetManager.open("spam_ham_dataset.csv");
                    File data = new File(getFilesDir(), "spam_ham_dataset.csv");
                    fos = new FileOutputStream(data);
                    buffer = new byte[1024];
                    while ((length = csvStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.flush();
                    fos.close();
                    csvStream.close();

                    // Run the script and pass the paths of the script and csv files as arguments
                    Process p = Runtime.getRuntime().exec(new String[]{"python", script.getAbsolutePath(), data.getAbsolutePath()});
                    String scriptresponse = new BufferedReader(new InputStreamReader(p.getInputStream())).readLine();
                    Log.d("hello", scriptresponse);


                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));

// read the output from the command
                    String s = null;
                    while ((s = stdInput.readLine()) != null) {
                        Log.d("Script Output:", s);
                    }

// read any errors from the attempted command
                    while ((s = stderr.readLine()) != null) {
                        Log.e("Script Error:", s);
                    }


                    // Display the output of the script in a TextView
                    TextView textView = (TextView) findViewById(R.id.message_display);
                    textView.setText(scriptresponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                // Permission is denied, show a message to the user
                // ...
                Toast.makeText(this, "External storage permission required", Toast.LENGTH_SHORT).show();
            }
        }


    }
}


























//
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("Python/pythonProject/main.py");
//        PyObject result = interpreter.get("result");
//
//
//        String output = result.toString();
//        TextView outputTextView = findViewById(R.id.textView);
//        outputTextView.setText(output);
