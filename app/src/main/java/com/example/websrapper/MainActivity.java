package com.example.websrapper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    EditText nameeingabe;
    TextView vornameview,nachnameview,gebdatview,gebortview,arbeit_unternehmenview,beschreibungview;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        nameeingabe = findViewById(R.id.editTextTextPersonName);
        vornameview = (TextView)findViewById(R.id.textView);
        nachnameview = (TextView)findViewById(R.id.textView3);
        gebdatview = (TextView)findViewById(R.id.textView5);
        gebortview = (TextView)findViewById(R.id.textView7);
        arbeit_unternehmenview = (TextView)findViewById(R.id.textView9);
        beschreibungview = (TextView)findViewById(R.id.textView11);
        imageView = findViewById(R.id.imageView);

    }
}