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
    String server = "10.171.154.144";
    Boolean buttonpressed=false;
    Boolean check = false;
    ArrayList<String> read = new ArrayList();
    Boolean gotinfo = false;

    EditText nameeingabe;
    TextView vornameview,nachnameview,gebdatview,gebortview,arbeit_unternehmenview,beschreibungview;
    ImageView imageView;
    String vorname;
    String nachname;
    String gebdat;
    String gebort;
    String arbeit_unternehemn;
    String beschreibung;
    String picurl;

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

        nameeingabe.setText("Name");

        new Thread(() -> {
            tcpcon();
        }).start();


        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {
                try{
                    hideSoftKeyboard();
                }catch(Exception e){
                    e.printStackTrace();
                }

                buttonpressed=true;
                new Thread(() -> {
                    while(true){
                        if(gotinfo){
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    vornameview.setText("Vorname: "+vorname);
                                    nachnameview.setText("Nachname: "+nachname);
                                    gebdatview.setText("Geburtsdatum: "+gebdat);
                                    gebortview.setText("Geburtsort: "+gebort);
                                    arbeit_unternehmenview.setText("Arbeit/Unternehmen: "+arbeit_unternehemn);
                                    beschreibungview.setText("Beschreibung: "+beschreibung);

                                    System.out.println("------------------------------------------------" + picurl);
                                    if(picurl!=""){
                                        Picasso.get()
                                                .load(picurl)
                                                .resize(750, 750)
                                                .centerCrop()
                                                .into(imageView);
                                    }
                                }
                            });
                            gotinfo=false;
                        }
                    }
                }).start();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public void tcpcon(){
        char a ;
        Socket socket = null;
        try {
            socket = new Socket( server, 1234);
            PrintStream out = new PrintStream( socket.getOutputStream() );
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            while(true){
                if(buttonpressed){
                    if(check){
                        break;
                    }
                    out.println(nameeingabe.getText().toString());
                    out.flush();

                    a = (char)in.read();
                    String File=" ";
                    while(a != (char)94) {
                        if((int) a == 124){
                            read.add(File);
                            File = "";
                        }else{
                            File = File + a;
                        }
                        a = (char) in.read();
                    }
                    vorname = read.get(0);
                    nachname = read.get(1);
                    gebdat = read.get(2);
                    gebort = read.get(3);
                    arbeit_unternehemn = read.get(4);
                    beschreibung = read.get(5);
                    picurl=read.get(6);
                    gotinfo=true;

                    buttonpressed=false;
                }
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}