package com.example.websrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextTextPersonName);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            String nam = name.getText().toString();
            public void onClick(View v) {
                System.out.println(nam);
            }
        });


    }

}