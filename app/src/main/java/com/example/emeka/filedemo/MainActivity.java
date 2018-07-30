package com.example.emeka.filedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*

I used external files to store data from the app instead of databases.
The file that contains the saved data is always stored in the internal memory of the device.

 */


public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        textView.setVisibility(View.GONE);
    }

    public void writeMessage(View view)
    {
        String Message = editText.getText().toString();
        String file_name = "hello_file";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Message Saved", Toast.LENGTH_SHORT).show();

            editText.setText("");

            textView.setVisibility(View.GONE);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage(View view)
    {
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("hello_file");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while ((Message=bufferedReader.readLine()) != null)
            {
                stringBuffer.append(Message + "\n");
            }

            textView.setText(stringBuffer.toString());
            textView.setVisibility(View.VISIBLE);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
