package com.engliplan.telelabclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client=Client.getInstance();
        client.addOnCommandReceivedListener(new Client.OnCommandRecievedListener() {
            @Override
            public void onCommandRecieved(String command) {
                Toast.makeText(MainActivity.this,command,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this,error,Toast.LENGTH_LONG).show();
            }
        });
    }
}